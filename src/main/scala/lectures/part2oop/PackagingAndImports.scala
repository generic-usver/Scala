package com.usver
package lectures.part2oop

import lectures.part1basics.DefaultArgs

// curly brace imports for grouped imports
import com.usver.playground.{Cinderella => Princess, PrinceCharming}
import com.usver.playground._ // wildcard!
//import com.usver.playground.* // Java wildcards don't work :/

import java.util.Date
import java.sql.{Date => SQLDate} // to avoid name collision

object PackagingAndImports extends App {

  // same package members are accessible by their names
  // traits, classes, etc

  // you can import the package if in other
  DefaultArgs.fact10

  // you can also use the fully qualified name (FQDN)
  com.usver.playground.ScalaPlayground.executionStart

  // ======================================
  // Scala-specific things: PACKAGE OBJECT
  // ======================================

  sayHello
  println(s"Speed of light: $SPEED_OF_LIGHT")

  // package importing
  val princess = new Princess() // import name aliasing
  val cinderella = new Cinderella()
  val prince = new PrinceCharming()

  // Default imports:
  // java.lang.String
  // java.lang.Object
  // java.lang.Exception

  // Scala default imports:
  scala.Int
  scala.BigDecimal
  scala.AnyRef

  scala.Predef.println // this is also imported by default in Scala
}
