package com.usver
package lectures.part3fp

object HOFsCurries extends App {

  //val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = ???

  // High Order Function examples:
  // map, flatMap, filter -- these take a function as a parameter


  // function that applies a function N times over a value x
  def nTimes(func: (Int => Int), times: Int, value: Int): Int = {
    if (times <= 0) value
    else nTimes(func, times - 1, func(value))
  } // Wooh! this was fun! ;)

  // test callable function
  val justDouble = (value: Int) => value * 2
  println("nTimes(1) x 3 == " + nTimes(justDouble, 3, 1))

  // nTimesBetter(f,n) = x => f(f(f(f..)))

  // Passing along basic params (func and times) + callback
  def nTimesBetter(func: (Int=>Int), times: Int): /* Define a function to return */ (Int => Int) = {
    if (times <= 0) /*This is the passed function*/ (x: Int) => x
    else (x: Int) => /*return other function*/ nTimesBetter(func, times-1)(func(x))
  }

  var plus10 = nTimesBetter(justDouble, 3)
  println("plus10(1) == " + plus10(1))

}