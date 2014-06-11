## Class #1

### Hello, Scala

There is a tradition in programming language pedagogy that the first
program you write should display the phrase "Hello, world". Let's learn
to do that now.

First, launch the REPL with `./sbt console`. Then, at the prompt, type
`"Hello, world"` and press `Return`. You should see the message
displayed back to you without the quotes.

```
[info] Loading global plugins from /Users/osheim/.sbt/0.13/plugins
[info] Set current project to meetup-scala-class (in build file:/Users/osheim/meetup-scala-class/)
[info] Starting scala interpreter...
[info]
Welcome to Scala version 2.10.4 (Java HotSpot(TM) 64-Bit Server VM, Java 1.7.0_45).
Type in expressions to have them evaluated.
Type :help for more information.

scala> "Hello, world"
res0: String = Hello, world
```

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

// division [1]
22980.0 / 52

// remainder
44 % 7
```

Feel free to play around with these until you are satisfied that Scala
can correctly do basic arithmetic.

[1] You may notice that division doesn't work as you expect. If you try
to divide `20 / 3` you'll get `6` instead of `6.66...`. To get more
intuitive division, you'll want to add decimal points to your numbers.
`20.0 / 3.0` returns the expected result.

### A rose by any other name

It's often useful to give values names, either to "save" a calculation
or to help describe what it is. In Scala we can bind a value to a name
using `val`. Here is an example that calculates what someone who works
35 hours per week at the Federal minimum wage makes per year (a
depressingly low number):

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
wages(17.0, 35)
```

When we define a function in Scala using `def` we call it a *method*.
As you can see, functions allow us to avoid repeating ourselves. We can
focus on the details that change, not the ones that are repeated.

The words `Double` and `Int` are called *types*. These tell us what
kinds of values we expect to be using. Both are numbers, but `Double`
allows us to use fractional values like `7.25`, while `Int` only allows
whole numbers to be used. The `wages` function specifies a type for
each of its parameters (`hourlyWage` and `hoursPerWeek`) and also
specifies function's *return type* (the kind of values it calculates).

Functions can take as many parameters as we want. Here's another
method that calculates the Pythagorean theorem. This function
calculates the hypotenuse of a right triangle (`z`) given the two
other sides (`x` and `y`):

```scala
def pythagoras(x: Double, y: Double): Double =
  math.sqrt(x * x + y * y)

pythagoras(1.0, 1.0)
pythagoras(3.0, 4.0)
pythagoras(1.0, 2.0)
```

The `pythagoras` function uses a built-in method called `math.sqrt` to
calculate the square-root. Scala provides many such built-in methods.

### Simplify, simplify

There are several different units to measure temperature (although
some are used more widely than others). Kelvin (K) is a scientific
unit that starts at 0K ("absolute zero") and goes up from
there. Celsius (C) is defined on the same scale, where 0C = 273.15K. A
mathematical equation might look like this:

```
Celsius = Kelvin - 273.15
```

We can write Scala functions to convert between Kelvin and Celsius:

```scala
def kelvinToCelsius(k: Double): Double = k - 273.15
def celsiusToKelvin(c: Double): Double = c + 273.15

kelvinToCelsius(0.0) // absolute zero
celsiusToKelvin(100.0) // boiling point of water

```

Fahrenheit (F) is a bit trickier, since it involves not just an offset
from Kelvin/Celsius (addition) but also a change in scale
(multiplication). Again, the equation migth be:

```
Fahrenheit = (Celsius x 1.8) + 32
```

The Scala functions look pretty similar:

```scala
def celsiusToFahrenheit(c: Double): Double = (c * 1.8) + 32
def fahrenheitToCelsius(f: Double): Double = (f - 32) / 1.8

fahrenheitToCelsius(32.0) // freezing point of water
celsiusToFahrenheit(100.0) // boiling point of water
```

Notice that we can write the conversion from Fahrenheit to Kelvin in
terms of the functions we already have:

```scala
def kelvinToFahrenheit(k: Double): Double = celsiusToFahrenheit(kelvinToCelsius(k))
def fahrenheitToKelvin(f: Double): Double = celsiusToKelvin(fahrenheitToCelsius(f))
```

### Simplify, simplify

There are many other possible units for measuring temperature:

```
Rankine = (Celsius + 273.15) x 1.8
Delisle = (100 - Celsius) x 1.5
Newton = Celsius x 0.33
Réaumur = Celsius x 0.8
Rømer = (Celsius x 0.525) + 7.5
```

We could define functions for every possible conversion, which would
be tedious. Also, users may be interested in other units that aren't
included here. How can we solve this problem?

One option is to generalize the trick we used earlier (composing
previously defined functions) to enable us to allow users to provide
their own conversions. Scala lets us pass functions as parameters, so
we can define methods like the following:

```scala
def freezing(fromCelsius: Double => Double): Double = fromCelsius(0.0)
def boiling(fromCelsius: Double => Double): Double = fromCelsius(100.0)

// identity is a function that just returns the value it is given
val celsiusFreezing = freezing(identity)
val celsiusBoiling = boiling(identity)

def celsiusToNewton(c: Double) = c * 0.33
val newtonFreezing = freezing(celsiusToNewton)
val newtonBoiling = boiling(celsiusToNewton)

def celsiusToRomer(c: Double) = (c * 0.525) + 7.5
val romerFreezing = freezing(celsiuToRomer)
val romerBoiling = boiling(celsiusToRomer)
```

If we also define functions from Celsius to our units, we can write a
flexible `convert` function that can go between any temperature system:

```scala
def convert(t: Double, toCelsius: Double => Double, fromCelsius: Double => Double) =
  fromCelsius(toCelsius(t))
  
def newtonToCelsius(n: Double) = n / 0.33
def romerToCelsius(ro: Double) = (ro - 7.5) / 0.525

// convert 23.1 Rømer into Newtons
convert(23.1, romerToCelsius, celsiusToNewton)

// convert 4.0 Newton into Kelvin
convert(4.0, newtonToCelsius, celsiusToKelvin)
```

As you can see, we can define new conversions to/from Celsius later,
and still use them with our existing code.

### The (type) safety dance

So things are going well, we have a lot of flexibility and the ability
to define new units of measurement. So we're done right?

Not so fast. There is one problem with our API: it would be easy to
forget which type of units we're using, since all the quanitites are of
type `Double`. This is not an abstract problem. NASA lost a $125M
orbiter due to a mix-up between English units and metric units
(pound-seconds versus newton-seconds).

We can fix this by introducing our own types. Instead of using `Double`
we can create types that store the unit information as well as the
amount.

The simplest way to do this is using a `case class`. This allows us to
wrap one or more values together into a new value. For example, here's
a simple way to define a geographical point:

```scala
case class GeoPoint(latitude: Double, longitude: Double)
```

We can use this class to define point points of interest:

```scala
val NewYorkCity = GeoPoint(40.7127, -74.0059)
val LosAngeles = GeoPoint(34.0500, 118.2500)
val RolandIowa = GeoPoint(42.1656, 93.5000)
```

Using `.latitude` and `.longitude` we can access the individual fields
of the class:

```scala
val x = NewYorkCity.latitude
val y = NewYorkCity.longitude
```

We can also access all of the fields using a different syntax (a
`destructuring pattern match`):

```scala
val GeoPoint(x, y) = NewYorkCity

// x and y are now available to be used
```

# Collections
The default collections available in scala are immutable.  While copying
an entire data structure to add an element seems expensive, Scala's
collections are persistent and use structural sharing to make these
operations fast.

## List
Let's checkout a few techniques for building a `List` of wages.

```scala
scala> val wages = List(7.25, 11.0, 13.0)
wages: List[Double] = List(7.25, 11.0, 13.0)

scala> val wages = 7.25 :: 11.0 :: List(13.0)
wages: List[Double] = List(7.25, 11.0, 13.0)
```


### Collection Members
Next we'll cover a few functions we'll find in several types of collections.  These methods will allow us to manipulate or determine properties of our collections.


Let's start with discovering a few properties of our new `List`.

* **exists** - Tells us if a certain element exists in the
collection, but unlike contains fakes a qualifying predicate function.

Let's take the following method that tells us if a wage is less than
minimum wage and apply it to our `List`.


```scala
def lessThanMinimum(wage: Double) = wage < 9.0

scala> wages.exists(lessThanMinimum)
res0: Boolean = true
```

* **forall** - Similar to exists but returns if all elements meet our
predicate condition.

Using our existing minimum wage function, let's find out if all the wages
in our list match this condition.

```scala
scala> wages.forall(lessThanMinimum)
res2: Boolean = false
```


Now that we know our List contains some minimum wages, let's pull those out using the same  function (`lessThanMinimum`) as before.


* **filter** - Filter to return all the elements that match the provided predicate.

```scala
scala> wages.filter(lessThanMinimum)
res2: List[Double] = List(7.25)
```


Now that we have a list with our minimum wages what about the other values?  We'll probably want to use them independently as well.  Our next transformative function will help with that.
* **partition** - Splits our collection into two based on the the predicate.
```scala
scala> wages.partition(lessThanMinimum)
res2: (List[Double], List[Double]) = (List(7.25),List(11.0, 13.0))
```

Bam! The seas have been parted and partition has now provided us with two brand new lists.  One that satisfies the `lessThanMinimum` predicate and the rest.



![alt tag](http://www.gaypatriot.net/wp-content/uploads/2014/02/professor-farnsworth.jpg)

We're getting payraises of a whopping 20% across the board.  Let's do some transformations on our `List` to see what this will look like.

We'll start by defining our function that will increase our wages by 20%.
```scala
def raisePay(wage: Double) = wage * 1.20
```

Now we can use this function to transform the values in our wages list with `map`.

* **map** - Builds a new collection by applying the provided function to all the elements in this one.

```scala
scala> wages.map(payRaise)
res2:  List[Double] = List(8.7, 13.2, 15.6)
```

* flatten
* flatMap

## Map
### Build it
### Pull things
* Map of cities with temperatures
* What is an option?

## Option
* map
* Map Option map or Map map Option
* Living without NPEs
* apply/Some

## for comprehensions
* Simple comprehension with List
  * Previous examples with list.
* Complex map/flatMap example cleanup

## Case classes
* Write a trait for Temperature
* Pattern matching of class types
