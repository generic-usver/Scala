package com.usver
package exercises

abstract class MyList {
  def head(): Int
  def tail(): MyList
  def isEmpty(): Boolean
  def add(int: Int): MyList
  protected def printElements: String
  override def toString(): String = s"[${printElements}]"
}

class EmptyList() extends MyList {
  override def head(): Int = throw new NoSuchElementException
  override def tail(): MyList = throw new NoSuchElementException
  override def isEmpty(): Boolean = true
  override def add(int: Int): MyList = new MyArrayList(int, this)
  override protected def printElements: String = ""
}

class MyArrayList(val headElement: Int, list: MyList) extends MyList {
  override def head(): Int = headElement
  override def tail(): MyList = list
  override def isEmpty(): Boolean = false
  override def add(int: Int): MyList = new MyArrayList(int, this)

  override protected def printElements: String = {
    s"head=>$head tail: ${this.tail()}"
  }
}

object ListTest extends App {
  println("Starting ..")

  val arr = new EmptyList()
  println("Arr = " + arr)

  val arr2 = arr add 1
  println("Arr = " + arr2)

  val arr3 = arr2 add 5
  println("Arr = " + arr3)

  val arr4 = arr3 add 11
  println("Arr = " + arr4)

}

