## Class #1

### Hello, Scala

There is a tradition in programming language pedagogy that the first
program you should write should display the phrase "Hello, world".
Let's learn to do that now.

First, launch the REPL with `./sbt console`. Then, at the prompt, type
`"Hello, world"` and press `Return`. You should see the message
displayed back to you without the quotes.

### Comments welcome

Scala ignores lines that begin with `//`. Throughout these examples
comments will be used to help explain what is happening, or to provide
context. You don't need to enter these lines into the REPL (although it
is fine to do so -- they will be ignored).

### I'm the operator of my pocket calculator

In addition to being a powerful functional programming language, Scala
is also an efficient calculator. It supports most basic operations
you'd expect. Let's try it out!

```scala
// addition
1 + 2 + 3 + 4

// subtraction
1000 - 245

// multiplication
7.25 * 35

// division
22980.0 / 52

// remainder
44 % 7
```

### A rose by any other name

It's often useful to give values names, either to "save" a calculation
or to help describe what it is. In Scala we can bind a value to a name
using `val`. Here is an example that calculates what someone who works
35 hours per week at the Federal minimum wage makes per year:

```scala
val minimumWage = 7.25
val hoursPerWeek = 35
val weeksPerYear = 52

minimumWage * hoursPerWeek * weeksPerYear
```

As you can see, good names help document what values mean, and how they
will be used. In Scala the convention is to use "camel-case" for names,
meaning each word is squashed together, and all but the first word are
capitalized.

### What's your function?

The previous example showed us how to calculate yearly income from an
hourly wage and weekly hours. We can generalize our example by writing
a function that performs the calculation for us:

```scala
val weeksPerYear = 52

def wages(hourlyWage: Double, hoursPerWeek: Int): Double =
  hourlyWage * hoursPerWeek * weeksPerYear

wages(7.25, 35)
wages(11.0, 35)
wages(13.0, 35)
```

When we define a function in Scala using `def` we call it a *method*.
As you can see, functions allow us to avoid repeating ourselves. We can
focus on the details that change, not the ones that are repeated.

The words `Double` and `Int` are called *types*. These tell us what
kinds of values we expect to be using. Both are numbers, but `Double`
allows us to use fractional values like `7.25`, while `Int` only allows
whole numbers to be used.

Functions can take as many parameters as we want. Here's another method
that calculates the Pythagorean theorem. This function calculates the
hypotenuse of a right triangle given the two other sides (`x` and `y`):

```scala
def pythagoras(x: Double, y: Double): Double =
  math.sqrt(x * x + y * y)

pythagoras(1.0, 1.0)
pythagoras(3.0, 4.0)
pythagoras(1.0, 2.0)
```

The `pythagoras` function uses a built-in method called `math.sqrt` to
calculate the square-root. Scala provides many such built-in methods.
