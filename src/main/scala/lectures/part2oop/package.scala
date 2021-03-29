package com.usver
package lectures

// Only one such "package object" is allowed per package
package object part2oop {

  // Very Scala-specific thing


  // These things will be available in the whole package
  // If you don't want to have functionality in any class - throw it here
  def sayHello: Unit = println("Hello, Scala!!!")
  val SPEED_OF_LIGHT = 299792458 // metres per second
}
