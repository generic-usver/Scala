package com.usver
package lectures.part2oop

object MethodNotations extends App {

  class Person(val name: String, favoriteMovie: String) {
    def likes(movie: String): Boolean = movie == favoriteMovie
    def hangOutWith(person: Person): String = s"$name hangs out with ${person.name}"
    def unary_! : String = s"$name inverting!" // prefix notation - operators as prefixes
    def isAlive: Boolean = true
    // apply() with 0 parameters is a special function
    def apply(): String = s"Hi, my name is $name and my movie is $favoriteMovie"
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
}
