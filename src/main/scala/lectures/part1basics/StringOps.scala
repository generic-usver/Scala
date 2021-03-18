package com.usver
package lectures.part1basics

object StringOps extends App {
  val str = "Hello, I am learning Scala"

  println(str.charAt(2))
  println(str.substring(7,11))
  println(str.split(" ").toList)
  println(str.startsWith("Hello"))
  println(str.replace(" ","_"))
  println(str.toLowerCase)
  println(s"string length: ${str.length}")
  // End of Java

  // Scala own methods
  val aNumberString = "45"
  println(aNumberString.toInt)
  println('a' +: aNumberString :+ 'z') // prepending
  println(aNumberString.reverse)
  println(str.take(5))

  // Scala-specific string interpolators

  // S-interpolators
  val name = "David"
  val age = 12
  val greeting = s"Hello, my name is $name and I am $age years old"
  println(s"Hello, my name is $name and I will be turning ${age + 1} years old")

  // F-interpolators
  val speed = 1.2f
  println(f"$name can eat $speed%2.2f burgers per minute")

  // Raw interpolator
  println(raw"This is a \n newline") // raw \n stays
  val escaped = "This is a \n newline"
  println(raw"$escaped") // \n parsed and formatted
}
