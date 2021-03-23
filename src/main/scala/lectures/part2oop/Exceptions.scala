package com.usver
package lectures.part2oop

import scala.util.Random

object Exceptions extends App {

  val x: String = null

  try {
    //println(x.length)
    //throw new Exception(":)")
  } catch {
     case e: Exception => println(s"${e.getMessage} :: Whoa, we almost crashed!")
  } finally {
    println("Ok, we are now past the exception zone")
  }
  // ^^ this will crash with NPE

  // [1] throw and catch exceptions

  // Throwable classes extend the Throwable class
  // Exception:   Something wrong with code
  // Error:       Something wrong with system. OutOfMemoryError, StackOverflowError

  def getInt(withExceptions: Boolean): Int = {
    if (withExceptions) throw new RuntimeException("exception while Inting")
    else new Random().nextInt()
  }

  println("getInt: " + getInt(false))


  try {
    getInt(true)
  } catch {
      case e: RuntimeException => println("Runtime exception!")
      case _: Exception => println("Default exception type!")
  }

}
