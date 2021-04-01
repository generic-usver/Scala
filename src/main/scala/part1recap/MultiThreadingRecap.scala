package com.usver
package part1recap

import scala.concurrent.Future
import scala.util.{Failure, Success}

object MultiThreadingRecap extends App {

  // default Java style
  val aThread = new Thread(new Runnable {
    override def run(): Unit = println("I'm running in parallel")
  })
  aThread.run()
  aThread.join()

  // syntactic Scala sugar:
  val aNotherThread = new Thread(() => println("I'm also running in parallel"))
  aNotherThread.run()

  val threadHello = new Thread(() => (1 to 1000).foreach(_ => println("hello")))
  val threadGoodbye = new Thread(() => (1 to 1000).foreach(_ => println("goodbye")))

  // different runs produce different results!
  class BankAccount(private var amount: BigDecimal) {
    override def toString: String = s"[Amount]: $amount"
    def withdraw(money: BigDecimal) = this.synchronized {
      this.amount -= money
    }
  }
  // amount can be made "@volatile" which solves concurrent READ problem. WRITE is still wrong :/

  // Atomic means thread-safe

  // wait() and notify() mechanism

  // Scala futures
  import scala.concurrent.ExecutionContext.Implicits.global
  val future = Future {
    // long computation
    42
  }

  future.onComplete {
    case Success(42) => println("Found the expected value")
    case Failure(_) => println("Exception while getting meaning")
  }

  // Future has monadic contract == has functional primitives

  val aProcessFeature = future.map(_ + 1) // we expect output from future and use it further on
  val aFlatFuture = future.flatMap { value =>
    Future(value + 2) // return a completely NEW Future
  }
  val aFilteredFeature = future.filter(_ % 3  == 0) // fail with NoSuchElementEXCEPTION

  // Combining futures
  val aNonsenseFeature = for {
    meaningofLife <- future
    filteredMeaning <- aFilteredFeature
  } yield meaningofLife + filteredMeaning


}
