package com.usver
package lectures.part2oop

object AbstractDataTypes extends App {

  // Classes with abstract methods == Abstract classes

  abstract class Animal {
    val creatureType: String // no value == abstract
    def eat: Unit // no implementation == abstract
  }

  class Dog extends Animal {
    override val creatureType: String = "dog"
    override def eat: Unit = println("[Dog eating sounds]")
  }

  // Traits == inheritable interface.
  trait Carnivore {
    def eat(animal: Animal): Unit
  }

  class Crocodile extends Animal with Carnivore {
    override val creatureType: String = "croc"
    override def eat: Unit = println("[Croc eating sounds")

    override def eat(animal: Animal): Unit = println(s"Croc eating ${animal.creatureType}")
  }

  val dog = new Dog
  dog.eat
  val croc = new Crocodile
  croc eat dog

  // Traits vs Abstract classes

  // +Both can have both abstract + implemented members
  // 1 - Traits don't have constructor parameters
  // 2 - Class can have multiple traits; Only 1 interface
  //    with Trait1 with Trait2
  // 3 - traits => describe behavior; Interfaces = "thing"

 // ===================================== //
 // Scala data types
 // ===================================== //

  // scala.Any == parent to everything
  // scala.AnyRef == java.lang.Object. String, List, Set ..
  //    scala.Null == special case

  // scala.AnyVal (primitive types)
  // Int, Unit, Boolean, Float. Rarely touched.

  // scala.Nothing == subtype of everything.

}
