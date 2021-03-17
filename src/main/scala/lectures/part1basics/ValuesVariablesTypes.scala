package com.usver
package lectures.part1basics

// object not class: better
// extends App == runnable
object ValuesVariablesTypes extends App {
  val x = 42 // types are optional - compiler guesses
  println(s"$x")

  val aString = "Hello"
  val aChar = 'a'
  val aShort: Short = 4322
  val aLong: Long = 3239823982983729839L

  val aFloat = 3.3f
  val aDouble = 3.14

  // variables

  var aVar: Int = 4
  aVar = 5 // side effects - variables can change


}
