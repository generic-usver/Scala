package com.usver
package part2actors

import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, Props}

object ActorsMessagesBehaviorsExercise1 extends App {
  import BankAccount._
  val actorSystem = ActorSystem("actorSystem")

  // Make a Counter actor with Increment/Decrement/Print methods
  val counter = actorSystem.actorOf(Props[CounterActor], "counterActor")
  counter ! Increment
  counter ! Increment
  counter ! Increment
  counter ! Decrement
  counter ! Print

  // Make a BankAccount actor with Deposit(amount), Withdraw(amount) Statement commands
  // It should respond with "Success" and "Failure" messages

  val account = actorSystem.actorOf(Props[BankAccount], "bankAccount")

  val client = actorSystem.actorOf(Props[AccountClient], "client")
  //client ! Statement
  client ! account
  client ! Deposit(10.10)
  client ! Statement()
  //client ! Deposit(-1.00)
  //client ! Deposit(4.30)
  //client ! Statement
}

class CounterActor extends Actor with ActorLogging {
  var value = 0
  lazy val name = context.self.path.name
  override def receive: Receive = {
    case Increment => value += 1
    case Decrement => value -= 1
    case Print => log.info(s"[$name]: $value")
  }
}

class AccountClient extends Actor with ActorLogging {
  import BankAccount._
  var account: ActorRef = null
  override def receive: Receive = {
    case Success => log.info("Success!")
    case Failure => log.info("Failure :/")
    case reference: ActorRef => account = reference
    case deposit: Deposit => account ! deposit
    case withdraw: Withdraw => account ! withdraw
    case statement: Statement => log.info("requesting statement"); account ! statement
    case decimal: BigDecimal => log.info(s"$decimal")
    //case other => log.warning(s"Neither success nor failure: ${other.getClass.getCanonicalName} : $other")
  }
}

class BankAccount extends Actor with ActorLogging {
  import BankAccount._
  var balance = BigDecimal(0.00)
  override def receive: Receive = {
    case Deposit(amount) => {
      if (amount > 0) {
        synchronized(balance) {
          balance += amount
          context.sender() ! Success
          balance.mc
        }
      } else {
        context.sender() ! Failure
      }
    }
    case Withdraw(amount) => {
        synchronized(balance) {
          if (amount > 0 && amount <= balance) {
            balance -= amount
            context.sender() ! Success
          } else {
            context.sender() ! Failure
          }
          balance.mc
        }
    }
    case Statement => {
      context.sender ! balance
    }
  }
}
object Increment
object Decrement
object Print

object BankAccount {

  object Success
  object Failure

  case class Withdraw(amount: BigDecimal) // replies with Success/Failure
  case class Deposit(amount: BigDecimal) // replies with Success/Failure
  case class Statement()
}