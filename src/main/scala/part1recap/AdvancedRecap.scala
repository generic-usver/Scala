package com.usver
package part1recap

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
  
}
