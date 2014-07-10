////////////////////////////////////////////////////////////////


// 1. Jane works 45 hours a week at $15.5/hour.
//    What are her yearly earnings?

// 2. Assuming overtime work gets paid at 1.5
//    times the normal rate, what are Jane's
//    yearly earnings?

// 3. Write a method that generalizes #2
def payWithOvertime(wage: Double, hours: Double): Double = ???

// 4. How many hours per week must Jane work
//    to earn $42,000 per year?


////////////////////////////////////////////////////////////////


// 5. Write a method that given a name (e.g. "Albert"),
//    produces a string of greeting (e.g. "Hello Albert").
def greet(name: String): String = ???

// 6. Modify the method to that produces a different greeting
//    for your team members' names (e.g. "Salutations Brian").

// 7. Write a method that returns the number of names in a list.
def numNames(names: List[String]): Int = ???

// 8. Write a method that determines if the given name exists
//    in a list of names.
def locate(given: String, names: List[String]): Boolean = ???


////////////////////////////////////////////////////////////////


// 9. Produce a single greeting for a list of names
def greet3(names: List[String]): String = ???

greet3(Nil)
// res0: String = "Hello!"

greet3("Alice" :: Nil)
// res1: String = "Hello Alice"

greet3("Alice" :: "Bob" :: Nil)
// res1: String = "Hello Alice and Bob"

greet3("Alice" :: "Bob" :: "Cate" :: Nil)
// res2: String = "Hello Alice, Bob, and Cate"


////////////////////////////////////////////////////////////////


// 10. Reimplement sumList but using Double instead of Int.

// 11. Rewrite sumList method to use tail recursion.
def sumList(ns: List[Double]): Double = {
  def loop(sofar: Double, xs: List[Double]): Double = ???
  loop(0.0, ns)
}

// 12. Implement a method to find the length of the list
def listLength(xs: List[Double]): Int = ???

// 13. Implement a way to reverse a list
def reverseList(xs: List[Double]): List[Double] = ???

// 14. Find the minimum value in a list
def minList(xs: List[Int], min: Int): Int = ???


////////////////////////////////////////////////////////////////



// 15. Find the minimum and maximum values in a list
def minMax(xs: List[Int], min: Int, max: Int): (Int, Int) = ???

// 16. Return only multiples of 3 (use % operator)
def onlyDivisibleBy3(xs: List[Int]): List[Int] = ???

// 17. Return whether n is a multiple of any of
//     the given divisors.
def divides(n: Int, divisors: List[Int]): Boolean = ???
// divides(3, Nil)            // false
// divides(3, 3 :: Nil)       // true
// divides(3, 2 :: Nil)       // false
// divides(20, 3 :: 7 :: Nil) // false
// divides(20, 5 :: 7 :: Nil) // true

// 18. Return only multiples of the divisors.
def divBy(xs: List[Int], divisors: List[Int]): List[Int] = ???


////////////////////////////////////////////////////////////////


// 19. Compute the mean (average) of a list
//     (Hint: look at sumList and listLength)
def mean(xs: List[Double]): Double = ???

// 20. If you didn't already, solve #19 using
//     a single tail-recursive inner function.

// 21. (Extra credit) Standard deviation is defined
//     as the square root of the average distance
//     from the average.
def stdDev(xs: List[Double]): Double = ???


////////////////////////////////////////////////////////////////


// 22. The Fibonacci sequence is defined as:
//       fib(0) = 0
//       fib(1) = 1
//       fib(n) = f(n-1) + f(n-2)
//     Implement it using recursion (fib(20) is 10946).
def fib(n: Int): Int = ???

// 23. (Extra credit) The traditional recursive solution
//     to #10 will evaluate f(n-2) twice, once on the (n)
//     step and once on the (n-1) step.
//
//     Implement a better version using an inner method.


////////////////////////////////////////////////////////////////


// 24. Your fib function probably only works for
//     values from 0 though 46 (for larger values
//     it likely produces negative numbers).
//
//     Implement fib10(n), a method that returns that
//     last digit of fib(n), and which does not have
//     this problem.
def fib10(n: Int): Int = ???

// fib10(51)    = 4
// fib10(503)   = 7
// fib10(5007)  = 8
// fib10(50003) = 7
