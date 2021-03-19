package com.usver
package lectures.part2oop

object OOBasics extends App {
  val person = new Person("Peter", 12)
  println(person.age)
  person.greet("Daniel")
  person.greet
}

//////////////////////////////
class Person(val name: String, val age: Int) {

  // Constructor
  // Can only call other constructors to work.
  def this(name: String) = this(name, 0)

  def greet(name: String) = println(s"${this.name} says Hi, $name!")

  def greet() = println(s"Hi, my name is $name")

  val JackLondon = new Writer("Jack","London", 1876)
  val TheWhiteFang = new Novel("The White Fang", 1906, JackLondon)

  println("white fang author age: " + TheWhiteFang.authorAge)

  val cnt1 = new Counter() // 0 inside
  val cnt2 = cnt1.increment(4)
  val cnt3 = cnt2.increment(5)
  val cnt4 = cnt3.decrement(2)

  println("after increment by 4: " + cnt2)
  println("after increment by 5 more: " + cnt3)
  println("after decrement by 2: " + cnt4)
}

class Writer(val firstName: String,
             val surname: String,
             val year: Int) {
  def fullName: String = s"$firstName $surname"
}

class Novel(val title: String, val year: Int, val author: Writer) {
  def authorAge = year - author.year
  def isWrittenBy(writer: Writer) = this.author == writer
  def copy(year: Int) = new Novel(this.title, year, this.author)
}

/*
  This was supposed to be a VAL class.
  Immutable - not modifiable inside
  So we drop that value
 */
class Counter(val count: Int = 0) {
  override def toString: String = s"Counter[$count]"
  def currentCount: Int = count
  def increment(): Counter = new Counter(count + 1)
  def decrement(): Counter = new Counter(count - 1)

  // recursively calling the same method just with smaller counter every time
  def increment(by: Int = 1): Counter = {
    if (by <= 0) this
    //else increment(by - 1) // this just loops around, not making real objects
    else increment.increment(by - 1) // this works with real Counter
  }
  def decrement(by: Int): Counter = {
    if (by <= 0) this
    else decrement.decrement(by - 1)
  }
}
