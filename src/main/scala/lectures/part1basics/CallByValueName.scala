package com.usver
package lectures.part1basics

object CallByValueName extends App {

  def callByValue(x: Long) = {
    println("byValue: " + x)
    println("byValue: " + x)
  }

  // Call by name dynamically re-evaluates on each call!
  def callByName(x: => Long): Unit = {
    println("by name: " + x)
    println("by name: " + x)
  }

  callByValue(System.nanoTime())
  callByName(System.nanoTime())

  ////////////////
  def infinite(): Int = 1 + infinite()
  def printFirst(x: Int, y: => Int) = println(x)

  // This tries to evaluate infinite() and fails
  printFirst(infinite(), 34)

  // This doesn't even care about => call without real need
  printFirst(34, infinite())
}
