package com.usver
package lectures.part3fp

object AnonymousFunctions extends App {

  // This is a predefined 1-argument Trait
  val doubler = new Function1[Int, Int] {
    override def apply(v1: Int): Int = v1 * 2
  }
  println(s"doubler: ${doubler(3)}")

  ////////////////////////////////////////////////////
  // anonymous function == LAMBDA
  val tripler: Int => Int // this is signature.
    = (x: Int) => x * 3
  println(s"tripler: ${tripler(3)}")

  val quadrupled: Int => Int = x => x * 4 // Int has been added in the beginning


  val twoNumberSumTripler: (Int, Int) => Int
    = (x: Int, y: Int) => (x + y) * 2

  val justDoSomething = () => 3 // no-parameter lambda

  println("plain: " + justDoSomething) // Lambdas can't be called without ()
  println("parenthesis: " + justDoSomething())  // Lambda understands only this

  // Curly braces - rarely used
  val stringToInt = { (str: String) =>
    str.toInt
  }

  val niceIncrementer: Int => Int = _ + 1 // default unnamed parameter
  val niceAdder: (Int, Int) => Int = _ + _ // a + b

  /*
    1) My list

   */

  val oldAdder: (Int) => Function1[Int, Int] = new Function1[Int, Function1[Int, Int]] {
        override def apply(x: Int): Function1[Int, Int] = new Function1[Int, Int] {
          override def apply(y: Int): Int = x + y  // O_o
        }
      }

  //println("oldAnonymous: " + oldAnonymous(3))
  println("oldAdder: " + oldAdder(3))
  println(oldAdder(3)(5))

  val superAdd = (x: Int) => (y: Int) => x + y // this is curried, but much shorter

  // also curried with optional type declaration
  val superAdder /*Int => Int => Int*/ = (x: Int) => (y: Int) => x + y

  println("superAdd(3): " + superAdd(3))
  println("superAdd(3)(5): " + superAdd(3)(5))
}
