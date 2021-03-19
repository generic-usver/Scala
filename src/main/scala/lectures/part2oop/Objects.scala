package com.usver
package lectures.part2oop

object Objects extends App {

  // SCALA does not have STATIC concept
  // Equivalent:
  object Person {

    val N_EYES = 2
    def canFly: Boolean = false

    // from == FACTORY METHOD
    // sole purpose = make new Instances
    def from(mother: Person, father: Person): Person = new Person("bobby")

    def apply(mother: Person, father: Person) = from(mother, father)
  }
  class Person(val name: String) {
    // instance-level functionality
  }
  // COMPANIONS == Object+class together

  println(Person.N_EYES)
  println(Person.canFly)

  // Scala object == SINGLETON
  val mary = new Person("mary")
  val john = new Person("john")

  println(mary == john)
  val person = new Person("person") // new instance
  val bobby = Person(mary, john)

  // Scala Applications == Scala object with
  // def main(args: Array[String]): Unit as parameters

  // Java: public static void main(String args[])
  // Scala: Object def main(args: Array[String])

  // def main(args: Array[String]): Unit

}
