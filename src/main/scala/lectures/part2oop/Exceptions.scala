package com.usver
package lectures.part2oop

import scala.util.Random
import org.scalatest.Assertions._

object Exceptions extends App {

  val x: String = null

  try {
    //println(x.length)
    //throw new Exception(":)")
  } catch {
     case e: Exception => println(s"${e.getMessage} :: Whoa, we almost crashed!")
  } finally {
    println("Ok, we are now past the exception zone")
  }
  // ^^ this will crash with NPE

  // [1] throw and catch exceptions

  // Throwable classes extend the Throwable class
  // Exception:   Something wrong with code
  // Error:       Something wrong with system. OutOfMemoryError, StackOverflowError

  def getInt(withExceptions: Boolean): Int = {
    if (withExceptions) throw new RuntimeException("exception while Inting")
    else new Random().nextInt()
  }

  println("getInt: " + getInt(false))


  try {
    getInt(true)
  } catch {
      case e: RuntimeException => println("Runtime exception!")
      case _: Exception => println("Default exception type!")
  } finally {
    // Finally doesn't affect return type of the expression
    // Finally are just for logging, etc
  }

  class MyException extends Exception
  val exception = new MyException

  //throw exception


  // 1. throw OOM
  //val array = Array.ofDim(Int.MaxValue)


  // 2. cause new SOError()
  def recursive(i: Int): Int = {
    recursive(i) + 1
  }
  //recursive(5)

  /*
    PocketCalculator
      - add(x,y)
      - subtract(x,y)
      - multiply(x,y)
      - divide(x,y)

    Throw
      - OverflowException if add(x,y) exceeds Int.MAX_VALUE
      - UnderflowException if subtract(x,y) exceeds Int.MIN_VALUE
      - MathCalculationException for division by 0
  */

  ////////////////////////////////////////////////
  class OverflowException
    extends RuntimeException("OverflowException! Result too large")

  class UnderflowException
    extends RuntimeException("UnderflowException! Result too negative")

  class MathCalculationException
    extends RuntimeException("MathCalculationException! Division by 0")
  ////////////////////////////////////////////////

  // Generalize PocketCalculator
  trait GenericCalculator {
    def add(x: Int, y: Int): Int // throws OverflowException
    def subtract(x: Int, y: Int): Int // throws UnderflowException
    def multiply(x: Int, y: Int): Int
    def divide(x: Int, y: Int): BigDecimal // throws MathCalculationException
  }

  object PocketCalculator {
    val MAX_ALLOWED_VALUE = Int.MaxValue
    val MIN_ALLOWED_VALUE = Int.MinValue
  }

  class PocketCalculator extends Object with GenericCalculator {
    @throws(classOf[OverflowException])
    override def add(x: Int, y: Int) = {
      val sum = x + y
      if (x > 0 && y > 0 && sum < 0) throw new OverflowException
      else if (x < 0 && y < 0 && sum > 0) throw new UnderflowException
      sum.intValue
    }
    @throws(classOf[UnderflowException])
    override def subtract(x: Int, y: Int) = {
      val diff = x - y
      if (x > 0 && (x > y) && y > 0 && diff < 0) throw new OverflowException()
      else if (x < 0 && y > 0 && diff > 0) throw new UnderflowException()
      diff
    }
    @throws(classOf[OverflowException])
    override def multiply(x: Int, y: Int) = {
      val imul = x * y
      println(s"imul($x, $y) == $imul")
      if (x > 0 && y > 0 && imul <= 0) throw new OverflowException()
      if (x < 0 && y < 0 && imul <= 0) throw new UnderflowException()
      else if (x < 0 && y > 0 && imul >= 0) throw new UnderflowException()
      imul
    }
    @throws(classOf[MathCalculationException])
    override def divide(x: Int, y: Int) = {
      if (y == 0) throw new MathCalculationException
      //val div = x / y // <-- testing data type glitches
      val div: BigDecimal = BigDecimal(x) / BigDecimal(y)
      div
    }
  }

  val calc = new PocketCalculator

  //
  assertThrows[MathCalculationException] {
    calc.divide(5, 0)
  }
  assertThrows[OverflowException] {
    calc.add(PocketCalculator.MAX_ALLOWED_VALUE, 1)
  }

  calc.add(PocketCalculator.MAX_ALLOWED_VALUE, -1) // OK

  assertThrows[UnderflowException] {
    calc.subtract(PocketCalculator.MIN_ALLOWED_VALUE, 1)
  }
  calc.subtract(PocketCalculator.MIN_ALLOWED_VALUE, -1)

  assertResult(0.2) {
    calc.divide(1, 5)
  }

  assertThrows[OverflowException] {
    calc.multiply(PocketCalculator.MAX_ALLOWED_VALUE, 2)
  }
  assertThrows[UnderflowException] {
    calc.multiply(PocketCalculator.MIN_ALLOWED_VALUE, 2)
  }


}
