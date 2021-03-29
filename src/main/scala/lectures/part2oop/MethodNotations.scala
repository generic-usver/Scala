package com.usver
package lectures.part2oop

import scala.language.postfixOps

object MethodNotations extends App {

  class Person(val name: String, val favoriteMovie: String, val age: Int = 0) {


    def likes(movie: String): Boolean = movie == favoriteMovie
    def hangOutWith(person: Person): String = s"$name hangs out with ${person.name}"
    def unary_! : String = s"$name inverting!" // prefix notation - operators as prefixes
    def isAlive: Boolean = true
    // apply() with 0 parameters is a special function
    def apply(): String = s"Hi, my name is $name and my movie is $favoriteMovie"
    // apply() method can take parameters
    def apply(times: Int): String = s"$name watched $favoriteMovie $times times"

    def +(title: String) : Person = new Person(s"$name ($title)", favoriteMovie, age)
    def unary_+ : Person = new Person(name, favoriteMovie, age + 1)

    def learns(subject: String) = s"$name learns $subject"
    def learnsScala() = this learns "Scala"
  }

  val mary = new Person("Mary","Inception")
  println(mary.likes("Inception"))
  println(mary likes "Inception") // wow,this is Infix notation == operator notation
  println(!mary)
  // this works only with 1-param methods
  // Infix notation == operator notation == Syntactic sugar

  /////////////////////////////////////////////////////////////
  // PREFIX NOTATION
  val tom = new Person("Tom","Fight club")
  println(mary hangOutWith tom) // quite a free syntax

  println(1.+(2)) // plus is a method under the hood!

  // Prefix notation
  val x = -1 // 1.unary_-
  val y = 1.unary_-
  // unary_  works only for some exceptions [+ - ~ !]

  /////////////////////////////////////////////////////////////
  // POSTFIX NOTATION
  println(mary.isAlive)
  println(mary isAlive) // works only with zero-parameter methods

  // APPLY METHOD
  println(mary.apply())
  println(mary()) // this calls APPLY method.

  // Exercise 1: Overload the + operator.
  // Mary + "the rockstar" => "Mary (the rockstar)"

  val title = "the rockstar"
  val maryRockstar = mary + title
  val expectedTitle = s"${mary.name} (${title})"

  assert(maryRockstar.name == expectedTitle,
    s"Exercise 1 title test FAIL! " +
      s"Expected: $expectedTitle, real: ${maryRockstar.name}"
  )

  // Exercise 2: Add an age to the person class
  //
  val maryOlder = +mary
  assert(maryOlder.age == mary.age + 1,
    s"Exercise 2 age test FAIL! " +
      s"Expected ${mary.age + 1}, real: $maryOlder.age")

  // Exercise 3: Add "learns" method in Person class: String = "$name learns $value"
  // learnsScala: learns("Scala")

  val learnSubject = "Scala"
  val expectedLearnText = s"${mary.name} learns $learnSubject"
  assert(mary.learns(learnSubject).equals(expectedLearnText))
  assert ((mary learnsScala).equals(expectedLearnText))

  // Exercise 4: apply() method(val: Int): String

  val times = 2
  val expectedXTimesOutput = s"${mary.name} watched ${mary.favoriteMovie} $times times"
  mary.apply(times).equals(expectedXTimesOutput)
}
