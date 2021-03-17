package com.usver
package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App {

  ////////////////////////////////////////////////////////////
  def factorial(n: Int): Int = {
    if (n <= 1) n else n * factorial(n - 1)
  }

  // Here we pass the "current sum" along with the factorial
  def anotherFactorial(n: Int): Int = {
    @tailrec
    def factorialValueAccumulator(current: Int, valueAccumulator: Int): Int = {
      if (current <= 1) valueAccumulator
      else factorialValueAccumulator(current - 1, current * valueAccumulator) // carry along the last sum
      // ^-- That's the TAIL recursion.
      // We don't rely on further recursion. We return value right away.
    }
    // use recursion as the last expression
    factorialValueAccumulator(n, 1)
  }

  (1 to 10).foreach(value => {
    //println(s"factorial($value): ${factorial(value)} :: ${anotherFactorial(value)}")
  })

  ////////////////////////////////////////////////////////////
  // WHEN YOU NEED LOOPS - USE TAIL RECURSION
  ////////////////////////////////////////////////////////////

  // Exercise 1: Concatenate String n times

  def imulString(string: String, times: Int): String = {
    @tailrec
    def stringAccumulator(str: String, times: Int, buffer: String): String = {
      if (times < 1) buffer
      else stringAccumulator(str, times - 1, buffer + str)
    }

    stringAccumulator(string, times, "")
  }

  //println(imulString("Hello", 5))

  // Exercise 2: IsPrime function with Tail recursion
  def isPrime(n: Int): Boolean = {
    @tailrec
    def canBeDividedWithSmallerNumbers(n: Int, smaller: Int): Boolean = {
      if (smaller <= 1) false
      else if (n % smaller == 0) true
      else canBeDividedWithSmallerNumbers(n, smaller - 1)
    }

    !canBeDividedWithSmallerNumbers(n, n - 1)
  }

  def getSymbol(bool: Boolean): String = if (bool) "✅" else "❌"

  val range = 1 to 20
  //range.foreach(f => println(s"[$f] => ${getSymbol(isPrime(f))}"))

  // Exercise 3: Fibonacci number calculation as Tail recursive

  def fib(n: Int): Int = {
    if (n <= 2) 1
    else fib(n - 1) + fib(n - 2)
  }

  def fibonacci(n: Int): BigDecimal = {

    // 1 + 1 + 2 + 3 + 5
    @tailrec
    def fibonacciFromCachedEntries
    (currentStep: Int, target: Int, previous: BigDecimal, prevPrevious: BigDecimal): BigDecimal = {
      //println(s"--Fibonacci for [$currentStep] = $previous + $prevPrevious")
      val currentValue = if (previous > 0) (previous + prevPrevious) else 1 + prevPrevious
      if (currentStep == target) {
        //println(s"--Fibonacci reached target ($currentStep), should be enough to calculate from $previous and $prevPrevious == probably $currentValue")
        currentValue
      } else {
        //println(s"currentStep = $currentStep, currentValue = $currentValue")
        fibonacciFromCachedEntries(currentStep + 1, target, currentValue, previous)
      }
    }
    fibonacciFromCachedEntries(1, n, 0, 0)
  }

  //(1 to 100).foreach(f => println(s"[$f] => ${fib(f)} :: ${fibonacci(f)}"))

  val expectedFibonacci100: BigDecimal = 354224848179261915075.0
  println(fibonacci(100))
}
