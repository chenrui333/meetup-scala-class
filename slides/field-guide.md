# meetup scala class

## a field guide

## 16 April 2015

---

We've seen and probably used basic pattern matching so far.

For example, we can destructure a tuple and compute the sum its members:

```scala
val tuple: Tuple2[Int, Int] = (1, 2)

val sum = 
  tuple match {
    case (x, y) => x + y
  }

// sum: Int = 3
```

---

Options are everywhere!

```scala
def helloOption(opt: Option[String]): String =
  opt match {
    case Some(x) => s"Hello $x" // interpolate if we are given a string
    case None    => "Who are you?"
  }
  
val some = helloOption(Option("human"))
// some: String = Hello human

val none = helloOption(None)
// none: String = Who are you?

val nullCons = helloOption(Option(null))
// nullCons: String = Who are you?
```

---

And we've most likely encountered lists and recursion:

```scala
// lists
def count[A](list: List[A]): Int = {
  @annotation.tailrec
  def rec(l: List[A], count: Int): Int =
    l match {
      case Nil => count
      case _ :: xs => rec(xs, count + 1)
    }
  rec(list, 0)
}

```

And probably also list destructuring:

```scala
def consy[A](list: List[A]) =
  list match {
    case x :: y :: z :: Nil      => 1
    case x :: y :: z             => 2
    case _                       => 3
  }
```

What would the results of these expressions be?

```scala
consy(List(1,2,3,4))
consy(List(1,2,3))
consy(List(1,2))
```

---

Pattern matching is implemented using what Scala calls _extractors_.

An _extractor_ is any object with a method (or methods) named `unapply`
and having the signature:

```scala
def unapply(s: String): Option[String]
```

Of course they need not be typed to `String`s.

They can also be parametric:

```scala
def unapply[A](a: A): Option[A]
```

---

We can make a parametric extractor to handle `null` checks in patterns:

```scala
object NotNull {
  def unapply[A](a: A): Option[A] = Option(a)
}
```

Scala allows us to arbitrarily next patterns:

```scala
List("Aristotle", null, "Plato") match {
  case _ :: NotNull(y) :: _ => println("ok")
  case _ => println("Second element is null :-(")
}

List(Option("Aristotle"), Some(null), Option("Plato")) match {
  case _ :: Some(NotNull(y)) :: _ => println("ok")
  case _ => println("Someone stuffed a null in a Some >:-(")
}
```

Nested patterns are great! You'll probably encounter some of these in meeps or
the API to match server-side code to HTTP requests.  

---

Let's talk about `case` classes. You've probably seen or even written
something like this:

```scala
case class Member(name: String)
```

And you know that you can magically use `Member` instances in pattern expressions:
 
```scala
val m = Member("Aristotle")

def memberName(m: Member): String =
  m match {
    case Member(n) => n
  }
  
val name = memberName(m)
// name: String = Aristotle

```

---

case classes are given special treatment by the compiler. Specifically, a
case class will be automatically fitted with:

* public immutable value members by default (its constructor arguments)
* `toString`, `equals`, and `hashCode` methods (in terms of its constructor arguments)
* `copy` method allowing easy, immutable transformations of its values
* a companion object, complete with an `unapply` method (in terms of its constructor arguments)

---

Our `Member` case class could have been equivalently defined like so:

```scala
class Member(val name: String) {
  override def hashCode(m: Member) = m.name.hashCode // not really
  override def equals(m: AnyRef) =
    m match {
      case mem: Member if m != null => name == mem.name
      case _ => false
    }
  override def toString() = s"Member($name)"
}

object Member {
  def unapply(m: Member): Option[String] =
    m match {
      case null => None
      case _ => Some(m.name)
    }
}
```

---

What about that weird list syntax?

`::` in Scala is both a method on `List` and a case class that subtypes of `List`.

This means that there is an object named `::` with an extractor defined automatically.

---

## PSA / VOODOO ALERT!

In Scala, methods that end with `:` associate to the right!

The same is true for extractors.

---

Let's define an explicit extractor to behave like the one generated for `::`:

```scala
object :::: {
  def unapply[A](x: List[A]): Option[(A, List[A])] =
    if(x.isEmpty) None
    else Some(x.head, x.tail)
}
```

We can use this using infix notation, just like `::`:

```scala
List(1, 2) match {
  case x :::: y :::: Nil => x + y
}
// res10: Int = 3
```

Or we can use prefix notation, as we could with `::`:

```scala
List(1, 2) match {
  case ::::(x, ::::(y, Nil)) => x + y
}
// res11: Int = 3
```

---

# For comprehensions

---

You've probably seen something like this:

```scala
scala> for(x <- 1 to 10) yield x * 2
// res11: scala.collection.immutable.IndexedSeq[Int] = Vector(2, 4, 6, ...
```

Or this, and then told that "it's bad" or "is not a for-loop":

```scala
var sum = 0
for(x <- 1 to 10) sum += x
// res12: Int = 55
```

---

And still this:

```scala
val a: Option[Int] = Some(1)
val b: Option[Int] = Some(2)
val c: Option[Int] = None

for { ai <- a; bi <- b } yield ai + bi
// res13: Option[Int] = Some(3)

for { ai <- a; bi <- b; ci <- c } yield ai + bi + ci
// res14: Option[Int] = None
```

---

What is the nature of this versatile `for` thing?

---

Let's take a detour and define a simple type for performing arbitrary validations.

```scala
sealed trait Disjunction[A, B] {
  def fold[X](l: A => X)(r: B => X): X =
    this match {
      case DisA(a) => l(a)
      case DisB(b) => r(b)
    }

  def flatMap[C](f: B => Disjunction[A, C]): Disjunction[A, C] =
    fold[Disjunction[A, C]](DisA(_))(f)

  def map[C](f: B => C): Disjunction[A, C] =
    flatMap { f andThen DisB.apply }
}

final case class DisA[A, B](a: A) extends Disjunction[A, B]
final case class DisB[A, B](b: B) extends Disjunction[A, B]
```

---

Now some functions to try it out:

```scala
def nonEmpty(s: String): Disjunction[String, String] =
  if(s.isEmpty) DisA("Empty!")
  else DisB(s)
  
def minLen(l: Int, s: String): Disjunction[String, String] =
  if(s.length < l) DisA("To short!")
  else DisB(s)
  
def maxLen(l: Int, s: String): Disjunction[String, String] =
  if(s.length > l) DisA("To long!")
  else DisB(s)
```

---

Now, this:

```scala
val s = "Hello"

for {
  ne <- nonEmpty(s)
  mn <- minLen(2, ne)
} yield mn
```

---

Is really this:

```scala
val s = "Hello"

nonEmpty(s)
  .flatMap { ne =>
    minLen(2, ne)
      .map { mn => mn }
  }
```

---

Let's decompile!

`./jd-gui`

---