package com.usver
package lectures.part1basics

object Functions extends App {

  // simple parameters
  def aFunction(a: String, b: Int): String =
    a + " " + b

  println(aFunction("Hello", 3))

  // inline functions
  def aParameterlessFunction() = 32

  println(aParameterlessFunction()) // standard call
  println(aParameterlessFunction) // no parenthesis

  /////////////////////////////////////
  // Recursive function
  def aRepeatedFunction(aString: String, n: Int): String = {
    if (n == 1) aString
    else aString + aRepeatedFunction(aString, n - 1)
  }
  // WHEN YOU NEED LOOPS, USE RECURSION
  // THAT'S A GOOD SCALA STYLE
  println(aRepeatedFunction("Hello", 3))

  ////////////////////////////////////////

  // Void function
  def aFunctionWithSideEffects(aString: String): Unit = println(aString)
  aFunctionWithSideEffects("side")

  ////////////////////////////////////////
  // FUNCTIONS CAN BE DEFINED INSIDE FUNCTIONS
  def aBigFunction(n: Int): Int = {
    def aSmallerFunction(a: Int, b: Int): Int = {
      a + b
    }
    aSmallerFunction(n , n - 1)
  }
  println("function inside: " + aBigFunction(4))

  ////////////////////////////////////////


  ////////////////////////////////////////
  // 2 exercises:
  // 1) Greeting String
  def aGreeting(name: String, age: Int) = s"Hi, my name is $name and I am $age years old"
  println(aGreeting("Oskars", 36))

  // 2nd exercise: Factorial (recursive)
  def factorial(n: Int): Int = {
    if (n <= 1) n
    else n * factorial(n-1)
  }
  val factValue = 5
  println(s"factorial(${factValue}): " + factorial(factValue))

  /* 3rd exercise: Fibonacci
    f(1) = 1
    f(2) = 1
    f(n) = f(n-1) + f(n-2)
  */
  def fibonacci(n: Int): Int = {
    if (n <= 2) 1
    else fibonacci(n-1) + fibonacci(n-2)
  }

  val fibValue = 8
  println(s"fibonacci($fibValue) = " + fibonacci(fibValue))

  // 4th Exercise: if number is prime
  def isPrime(n: Int): Boolean = {
    def canBeDividedWithSmallerNumbers(n: Int, smaller: Int): Boolean = {
      if (smaller <= 1) false
      else if (n % smaller == 0) true
      else canBeDividedWithSmallerNumbers(n, smaller - 1)
    }
    !canBeDividedWithSmallerNumbers(n, n-1)
  }
  def getSymbol(bool: Boolean): String = if (bool) "✅" else "❌"

  val range = 1 to 10
  range.foreach(f => println(s"[$f] => ${getSymbol(isPrime(f))}"))
}
