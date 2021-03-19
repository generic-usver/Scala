package com.usver
package lectures.part2oop

object Generics extends App {

  // A means Generics - probably like <T> in Java
  class MyMap[Key, Value] // also generic Map

  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]

  // generic methods
  class MyList[+A] { // using type A
    // B >: A :: Wider part is parent
    def add[B >: A](element: B): MyList[A] = ???
  }

  object MyList {
    def empty[A] = new MyList[A] // companion object with generic factory for MyList
  }

  //
  val emptyStrings = MyList.empty[String]
  val emptyInts = MyList.empty[Int]

  ////////////////////////////////////
  // Variance problem
  ////////////////////////////////////

  // Covariance meaning you can substitute types
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // Does List[Cat] extends a List[Animal] ?
  // 1 - YES, there is a way to implement this

  class CovariantList[+A] // +means type can be sub-typed
  val animalList: CovariantList[Animal]  = new CovariantList[Cat]
  // What about Java-type List<T extends Animal> ?
  // Oh, look way down - seem to be "Bounded" classes :)

  // 2 - NO, list of Cats isn't a list of Animals
  class InvariantList[A]
  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]

  // 3 - Hell, NO!
  // CONTRAVARIANCE :: seems kinda permissive O__O
  class ContravariantList[-A]
  val contravariantList: ContravariantList[Cat] = new ContravariantList[Animal]

  ////////////////////////////////////
  // Bounded types

  class Cage[A <: Animal](animal: A) // expects generic Animal or Animal subclass
  // <T extends Animal>

  val cage = new Cage(new Dog)
}
