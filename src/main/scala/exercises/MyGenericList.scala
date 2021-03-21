package com.usver
package exercises

abstract class MyGenericList[+A] {
  def head: A
  def tail: MyGenericList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyGenericList[B] // adding generic type
  protected def printElements: String
  override def toString(): String = s"[${printElements}]"
}

class EmptyGenericList[A]() extends MyGenericList {
  override def head: Nothing = throw new NoSuchElementException
  override def tail: Nothing = throw new NoSuchElementException
  override def isEmpty: Boolean = true
  override def add[A](element: A): MyGenericList[A] = new MyArrayGenericList(element, this)
  override protected def printElements: String = ""
}

class MyArrayGenericList[+A](val headElement: A, list: MyGenericList[A]) extends MyGenericList[A] {
  override def head: A = headElement
  override def tail: MyGenericList[A] = list
  override def isEmpty: Boolean = false
  override def add[B >: A](int: B): MyArrayGenericList[B] = new MyArrayGenericList(int, this)

  override protected def printElements: String = {
    s"head=>$head tail: ${this.tail}"
  }
}

object GenericListTest extends App {
  println("Starting ..")

  val arr = new EmptyGenericList()
  println("Arr = " + arr)

  val arr2 = arr add 1
  println("Arr = " + arr2)

  val arr3 = arr2 add "5"
  println("Arr = " + arr3)

  val arr4 = arr3 add "11"
  println("Arr = " + arr4)

  val listOfIntegers: MyGenericList[Int] = new EmptyGenericList()
  val listOfStrings: MyGenericList[String] = new EmptyGenericList()

  println("Adding int to empty: " + listOfIntegers.add(5))
  println("Adding String to empty: " + listOfIntegers.add("333"))


}

