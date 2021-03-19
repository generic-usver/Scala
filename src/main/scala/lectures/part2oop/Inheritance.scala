package com.usver
package lectures.part2oop

object Inheritance extends App {

  // single class inheritance - extend only 1 class
  sealed class Animal {
    val creatureType = "wild"
    def eat = print("nomnom")
  }

  class Cat extends Animal {
    def crunch = {
      println("crunch")
      eat
    }
  }

  val cat = new Cat()
  cat.crunch

  // Person

  class Person(name: String, age: Int) {
    // one-param constructor
    def this(name: String) = this(name, 0)
  }
  class Adult(name: String, age: Int, idCard: String)
    extends Person (name, age) {
  }

  // overriding
  // We can override it both in constructor param override
  // override val can be also in class body
  // That's perfectly the same thing
  class Dog(override val creatureType: String = "domestic") extends Animal {
    override def eat: Unit = {
      super.eat
      println("Overriding eating the Dog way")
    }
  }

  val dog = new Dog()
  dog.eat
  println(s"dog creature type: ${dog.creatureType}")

  //////////////////////////////////////////
  // Type substitution
  // Polymorphism - using general type instead of specific
  val unknownAnimal: Animal = new Dog("k9")
  unknownAnimal.eat


  // super() : parent class method

  /*
  PREVENTING overrides:
    1 - final class
    2 - final method
    3 - Sealed - can only modify in this very file
   */
}
