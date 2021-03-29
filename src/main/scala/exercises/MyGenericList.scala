package com.usver
package exercises

abstract class MyGenericList[+A] {
  def head: A
  def tail: MyGenericList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyGenericList[B] // adding generic type
  protected def printElements: String
  override def toString(): String = s"[${printElements}]"

  // Mapping to B. Input transformer knows A->B => list of B
  def map[B](transformer: A => B): MyGenericList[B]
  // Mapping to B. Transformer A to MyList[B] => MyList[B]
  // Look-ahead required here
  // Taking A list and returning similar A list.
  def filter(predicate: A => Boolean): MyGenericList[A]

  //concatenation
  def ++[B >: A](list: MyGenericList[B]): MyGenericList[B]
  def flatMap[B](transformer: A => MyGenericList[B]): MyGenericList[B]
}

/*
     1. Generic trait MyPredicate[T]
       = def test[T](T): Boolean
     2. Generic trait MyTransformer[A, B]

     Class EvenPredicate extends MyPredicate[Int]
       def test(Int)
     Class StringToIntTransformer[T]
  */

//////////////////////////////////
/*
trait MyPredicate[-T] { // T to Boolean
  def test(t: T): Boolean
}

trait MyTransformer[-A, B] { // A to B
  def transform(element: A): B
}*/
//////////////////////////////////



case object EmptyGenericList extends MyGenericList[Nothing] {
  override def head: Nothing = throw new NoSuchElementException
  override def tail: Nothing = throw new NoSuchElementException
  override def isEmpty: Boolean = true
  override def add[B >: Nothing](element: B): MyGenericList[B] = new MyArrayGenericList(element, EmptyGenericList)
  override protected def printElements: String = ""

  /*
     [1,2,3].map(_ * 2) = [2,4,6] // apply function to each
     [1,2,3,4].filter( n%2 == 0) = [2,4]
     [1,2,3].flatMap(n, n+1) = [1,2,2,3,3,4]
   */
  // Turning myList of A into MyList of B
  override def map[B](transformer: Nothing => B): MyGenericList[B] = EmptyGenericList
  override def flatMap[B](transformer: Nothing => MyGenericList[B]): MyGenericList[B] = EmptyGenericList
  override def filter(predicate: Nothing => Boolean): MyGenericList[Nothing] = EmptyGenericList
  override def ++[Nothing](list: MyGenericList[Nothing]): MyGenericList[Nothing] = list
}

case class MyArrayGenericList[+A](val headElement: A, list: MyGenericList[A]) extends MyGenericList[A] {
  override def head: A = headElement
  override def tail: MyGenericList[A] = list
  override def isEmpty: Boolean = false
  override def add[B >: A](int: B): MyArrayGenericList[B] = new MyArrayGenericList(int, this)


  //override def map[B](transformer: A => B): MyGenericList[B] = ???

  override def map[B](transformer: A => B): MyGenericList[B] =
    new MyArrayGenericList(transformer.apply(headElement), list.map(transformer))
  //override def flatMap[B](transformer: MyTransformer[A, MyGenericList[B]]): MyGenericList[B] =
  //  if (predicate.test(headElement)) {}
  override def filter(predicate: A => Boolean): MyGenericList[A] = {

    // We're taking LinkedList one by one starting from HeadElement.
    // If it matches - adding to return value.
    // If doesn't match - skip this HeadValue, filter out just

    // predicate isn't "predicate" class anymore.
    // It is now just an abstract FUNCTION with apply() inside.
    if (predicate.apply(headElement)) {
      println(s" >> $headElement matches predicate..")
      new MyArrayGenericList(headElement, list.filter(predicate))
    } else {
      println(s" >> $headElement doesn't match predicate..")
      list.filter(predicate)
    }
  }

  override protected def printElements: String = {
    s"$head $tail"
  }

  override def ++[B >: A](appendableList: MyGenericList[B]): MyGenericList[B] = {
    new MyArrayGenericList(headElement, tail ++ appendableList)
  }

  /*
    List [1,2,3].flatMap(n, n+1) = 1,2
   */
  override def flatMap[B](transformer: A => MyGenericList[B]): MyGenericList[B] = {
    transformer.apply(headElement) ++ tail.flatMap(transformer)
  }
}

object GenericListTest extends App {
  println("Starting ..")

  val arr = EmptyGenericList
  println("Arr = " + arr)

  val arr2 = arr add 1
  println("Arr = " + arr2)

  val arr3 = arr2 add "5"
  println("Arr = " + arr3)

  val arr4 = arr3 add "11"
  println("Arr = " + arr4)

  // I don't like that this maps to static Object instead of a proper Struct
  var listOfIntegers: MyGenericList[Int] = EmptyGenericList
  val listOfStrings: MyGenericList[String] = EmptyGenericList

  listOfIntegers = listOfIntegers.add(1)
  listOfIntegers = listOfIntegers.add(2)
  listOfIntegers = listOfIntegers.add(3)
  listOfIntegers = listOfIntegers.add(4)

  //println("Adding int to empty: " + listOfIntegers.add(5))
  //println("Adding String to empty: " + listOfIntegers.add(333))

  //
  //println("[1, 2, 3] doubled: " + Array(1,2,3).(_ * 2))
  //println("[1, 2, 3] filtered: " + Array(1,2,3).filter(EvenPredicate))

  println("Elements are now " + listOfIntegers)
  println("Will map them *= 2 and get output")
  println(listOfIntegers.map((element: Int) => element * 2))
  println("filtered even: " + listOfIntegers.filter((t: Int) => (t % 2 == 0)))


  println("Will concatenate " + listOfIntegers + " with " +listOfIntegers)
  println("result: " + (listOfIntegers ++ listOfIntegers))

  println("result: " + (listOfIntegers ++ listOfIntegers))

  println(s"flatMap from $listOfIntegers :: " + listOfIntegers.flatMap(new Function1[Int, MyGenericList[Int]] {
    override def apply(element: Int): MyGenericList[Int] =
      MyArrayGenericList(element, new MyArrayGenericList(element + 1, EmptyGenericList))
  }

  ))


}

