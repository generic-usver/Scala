package com.usver
package part2actors

import akka.actor.{Actor, ActorSystem, Props}

object ActorsIntro extends App {

  /////////////////////////////////
  // Basic Actors functionality
  /////////////////////////////////

  // PART 1 - create actor system
  // one system per application
  val actorSystem = ActorSystem("firstActorSystem") // no spaces in names
  println("actor system name: " + actorSystem.name)

  // PART 2 - create actors
  // Async communication. Probably defining callbacks

  // word count actor


  class WordCountActor extends Actor {
    var totalWords = 0
    def receive: PartialFunction[Any, Unit] = {
      case (message: String) =>
        println(s"I have received a sentence '$message'")
        totalWords += message.split(" ").length
      case (unreadable) => println(s"I can't understand word $unreadable")
    }
  }

  // PART 3 - INSTANTIATE OBJECTS

  val wordCounter = actorSystem.actorOf(Props[WordCountActor], "wordCounter") // name to be used internally (?)
  // Made an instance of ActorRef

  // PART 4 - COMMUNICATE WITH ACTORS

  // using infix notation == without dots
  wordCounter ! "I am learning AKKA and that's pretty cool!"

  // That "!" is "exclamation method" == "TELL" method

  class ActorPerson(name: String) extends Actor {
    override def receive: Receive = {
      case "hi" => println(s"Hi, my name is $name")
      case _ => println("Doing nothing")
    }
  }
  // Now .. how do we instantiate this? ^^

  // Legal, but not the best way
  val person = actorSystem.actorOf(Props(new ActorPerson("Bob")))
  person ! "hi"

  // "Best practice" - a better way to instantiate subclass of Actor
  // Make a companion object of this
  object ActorPerson {
    def props(name: String) = Props(new ActorPerson(name))
  }

  val johnPerson = actorSystem.actorOf(ActorPerson.props("John"))
  johnPerson ! "hi"
}

