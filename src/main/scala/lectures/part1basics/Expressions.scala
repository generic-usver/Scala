package com.usver
package lectures.part1basics

object Expressions extends App {

  val x = 1 + 3 // yields to Integer
  println(s"x: $x")

  println(2 + 3 * 4)
  // >>> right shift with zero extension

  println(1 == x)

  // == and !=
  println(!(1 == x))

  var aVar = 2
  aVar += 3

  ////////////////////////////////////
  // Instructions vs Expressions
  // Instruction (DO) == what to do

  // Expression (VALUE)
  // IF expression

  val aCondition = true
  val aConditionalValue = if (aCondition) 5 else 3
  // if expression == returns VALUE
  // if in Scala == Expression

  var i = 0
  while ( i < 10 ) {
    println(i)
    i += 1
  }

//  1 to 10 foreach {
//    println("alternative loop " + _)
//  }
  // NEVER WHILE LOOPS - THAT'S INFERIOR

  // EVERYTHING IN SCALA IS AN EXPRESSION

  val aWeirdValue = (aVar = 3) // Unit ! // also void

  println(s"Weird value[will be Unit]: $aWeirdValue")

  // expressions are  returning Unit .
  // Examples: println(), while(), reassigning

  // CODE BLOCKS

  // INSTRUCTION => DO SOMETHING
  // EXPRESSION =>  GIVE ME VALUE OF SOMETHING

  // inside is invisible to others
  val aCodeBlock = {
    val y = 2
    val z = y + 1

    if (z > 0) "hello" else "goodbye" // <-- this last thing is returned
  }

  // 1. difference between String "hello world" (it's string) and println("hello world")?
  // Answer: String is string but println returns Unit()

  val someValue = {
    2 < 3 // will return TRUE
  }

  val someOtherValue = {
    if (someValue) 239 else 986
    42 // will return this value
  }

  // someValue == TRUE
  // someOtherValue == 42

  println(s"someValue: $someValue, someOtherValue: $someOtherValue")
}
