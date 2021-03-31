package com.usver
package part1recap

import scala.util.Try
import lectures.part2oop.CaseClasses.Person

// extending App means it can be run
object GeneralRecap extends App {

  // FP == working with functions as first-class
  val incrementer = (x: Int) => x+1
  List(1,2,3).map(incrementer).foreach(println(_))
  // Map == High Order Function == takes function as parameter

  val pairs = for {
    num <- List (1,2,3,4)
    char <- List ('a','b','c','d')
  } yield num + "-" + char // Yield just composes/combines

  println("pairs: " + pairs)
  // == List(1,2,3,4).flatMap(num=>List('a','b','c','d')).map(char => num + "-" + char

  // Seq, Array, List, Vector, Map, Tuples, Sets

  // Option and Try

  val anOption = Some(2)
  val aTry = Try {
    throw new RuntimeException
  }

  // pattern matching
  val unknown = 2
  val order = unknown match {
    case 1 => "first"
    case 2 => "second"
    case _ => "unknown"
  }

  val bob = Person("Bob",22)
  val greeting = bob match {
    case Person(name, _) => s"Hi, my name is $name"
    case _ => "Well, unknown person here"
  }
}
