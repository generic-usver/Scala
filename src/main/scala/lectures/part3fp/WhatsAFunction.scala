package com.usver
package lectures.part3fp

import org.scalatest.Assertions._

import scala.util.Random

object WhatsAFunction extends App {
  // What's Function gotta do with it..

  // Use functions as first class elements
  // That means: use Functions just like plain Values

  def given = 5
  def funGiven = 15

  println(s"given, funGiven: $given $funGiven")

  /////////////////////////////////////////////
  //  Problem: functions are usually instances of classes
  println(new Action().execute(3))

  val doubler = new TraitWithApply[Int, Int] {
    // override def is important so this works!
    override def apply(element: Int): Int = element * 2
  }

  println("doubler of 2: " + doubler(2))

  // function types (1..22)

  // In Scala we can use " new Function() " to
  val stringToIntConverter = new Function[String, Int] {
    override def apply(string: String): Int = string.toInt
  }

  println(stringToIntConverter("7") + 3)

  // one Application supports up to 22 different signature Functions

  val adder = new ((Int, Int) => Int) {
  //val adder = new Function2[Int, Int, Int] {
    override def apply(add1: Int, add2: Int): Int = add1 + add2
  }

  val doubleDoubler = new ((Double, Double) => Double) {
    override def apply(d1: Double, d2: Double) = (d1 + d2) * 2
  }
  println(doubleDoubler(2.0, 3.0))



  // ALL SCALA OBJECTS ARE ACTUALLY FUNCTIONS UNDER THE HOOD :(

  /*
    1) two strings as arguments + concatenate them
   */

  val stringConcatenator = new ((String, String) => String) {
    override def apply(v1: String, v2: String): String = s"$v1$v2"
  }

  assert("aaabbb" equals stringConcatenator("aaa","bbb"))

  val intRandomizer = new (Int => Int) {
    override def apply(v1: Int): Int = Random.nextInt(100)
  }
  val inter = new (Int => Int) {
    override def apply(v1: Int): Int = intRandomizer(v1)
  }

  // Function1[Int, Function1[Int, Int])
  //

  val superAdder: (Int) => Function1[Int, Int] = new Function1[Int, Function1[Int, Int]] {
    override def apply(x: Int): Function1[Int, Int] = new Function1[Int, Int] {
      override def apply(y: Int): Int = x + y  // O_o
    }
  }

  val superAdder3 = superAdder(3)
  println("adder3: " + superAdder3) // a new function that doesn't trigger all cycle
  println("adder3(4) == " + superAdder3(4))  // Ok, now we've got both cycles triggered

  println(superAdder(3)(4)) // 2 layers of parameters - weird look
  // ^-- That's called a Curried function

  // this way we can call them recursively!!
  //val specialFunction2: (Int) => Function1[Int, Int] = (x: Int) => new Function1[Int, Int] {
  //  override def apply(y: Int): Int = x + y // O_o
  //}

  // Higher order functions -> Either receiver other functions as parameters
  // OR :: return other functions as result


  println("inter(4) = " + inter(4))
  println("inter(5) = " + inter(5))
}

class Action {
  def execute(element: Int): String = s">> $element <<"
}

// This is how JVM languages work.
// Scala has found some clever tricks
//  to look it like a true Functional Programming language
trait TraitAction[A,B] {
  def execute(element: A): B
}

trait TraitWithApply[A,B] {
  def apply(element: A): B
}
