package com.usver
package part1recap

import scala.concurrent.Future

object AdvancedRecap extends App {

  // partial functions are functions that operate only on part of the given domain

  val partialFunction: PartialFunction[Int, Int] = {
    case 1 => 42
    case 2 => 65
    case 5 => 999
  }
  // This WILL throw an Exception for other values (!!)

  val pf = (x: Int) => x match {
    case 1 => 42
    case 2 => 65
    case 5 => 999
  }

  println("modifiedList: ")
  /// THIS IS THE TOP SYNTAX THROUGHOUT THE SCALA AKKA
  val modifiedList = List(1,2,3).map {
    case 1 => 42
    case _ => 0
  }.foreach(println(_))

  // function lifting:

  val lifted = partialFunction.lift  // partial becomes TOTAL function Int => Option[Int]
  lifted(2)   // Some(64)
  lifted(20)  // None

  // orElse
  val partialFunctionChain = partialFunction.orElse[Int, Int] {
    case 60 => 9000
  }

  // orElse gives extra values to the "partially covered" values.
  // It can "compliment" or "extend" the original incomplete value set

  partialFunctionChain(5)   // 999 from the original
  partialFunctionChain(60)  // 9000 from "orElse" add-on
  //partialFunctionChain(456) // thows matchError

  // TYPE ALIASES
  type ReceiveFunction = PartialFunction[Any, Unit]

  // define a function declaring a well-known method signature
  def receive: ReceiveFunction = {
    // Getting integers as inputs
    case 1 => println("Hello")      // println() returning *Unit* back
    case 2 => println("confused")   // println() also returning *Unit*
  }

  //////////////////////////////
  def optionalToInt(in: String): Option[Int] = {
    try {
      Some(in.trim.toInt)       // Some == something inside
    } catch {
      case e: Exception => None // None == nothing inside
    }
  }

  optionalToInt("asdf") match {
    case Some(i) => println(s"Found Integer: $i")
    case None => println("input wasn't valid")
  }

  // IMPLICITS
  //
  implicit val timeout = 3000

  def setTimeout(f: () => Unit)(implicit timeout: Int) = {
    println("setting timeout: " + timeout)
    f()
  }
  setTimeout(() => println("setting a timeout")) // extra parameter]

  // IMPLICIT is a way to "kinda guess" the nearest "implicit" value to be used

  case class Person(name: String) {
    def greet = s"Hi, my name is $name!"
  }

  implicit def fromStringToPerson(name: String): Person = Person(name)
  println("Peter".greet) // this is automatically done by compiler

  implicit class Dog(name: String) {
    def bark = println(s"bark [$name]")
  }
  "Lassie".bark

  implicit val inverseOrdering: Ordering[Int] = Ordering.fromLessThan(_ > _)
  List(1,2,3).sorted.foreach(println _) // sorted takes implicit Ordering param

  import scala.concurrent.ExecutionContext.Implicits.global
  val future = Future {
    println("Hello from future")
  }

  /*
      Ordering in which implicit values are taken:
        1) Local scope
        2) Imported scope
        3) Companion objects
   */
}
