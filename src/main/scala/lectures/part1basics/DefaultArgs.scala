package com.usver
package lectures.part1basics

object DefaultArgs extends App {

  def trFact(n: Int, acc: Int = 1): Int = {
    if (n <= 1) acc
    else trFact(n-1, n*acc)
  }

  //val fact10 = trFact(10, 1)
  val fact10 = trFact(10)

  //def savePicture(format: String, width: Int, height: Int): Unit = println("saving picture")
  def savePicture(format: String = "jpg", width: Int = 600, height: Int = 800): Unit = println("saving picture")
  savePicture(width = 500)
}
