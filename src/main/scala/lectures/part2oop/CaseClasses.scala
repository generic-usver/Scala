package com.usver
package lectures.part2oop

object CaseClasses extends App {

  // This seems like Data classes @Kotlin :)
  case class Person(name: String, age: Int)

  // data class creation + toString()
  val jim = Person("Jim", 34)
  println(s"Jim: $jim")

  // equals and hashCode implemented already == Data class @Kotlin
  val jim2 = Person("Jim",34)
  println("they are equal: " + (jim == jim2))

  // Can be copied
  println("Jim copied: " + jim.copy(age = 33))

  // Companion objects
  val thePerson = Person

  val mary = Person("Mary",23) // this says "apply()" under the hood

  // Case classes are serializable
  // Akka does this a lot

  // Case classes have extractor patterns
  case object UnitedKingdom {
    def name: String = "some text"
  }
}
