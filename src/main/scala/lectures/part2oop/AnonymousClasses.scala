package com.usver
package lectures.part2oop

object AnonymousClasses extends App {
  abstract class Animal {
    def eat: Unit
  }

  // Nice inlinde classes - just like Java lambdas :)
  val funnyAnimal = new Animal {
    override def eat: Unit = println("Eating time, roar!")
  }

  println("funnyAnimal name: " + funnyAnimal.getClass)

  class Person(val name: String) {
    def sayHi = println(s"Hi, $name!")
  }

  val jim = new Person("Jim") {
    override def sayHi: Unit = println(s"Hi, my name is $name!")
  }


}
