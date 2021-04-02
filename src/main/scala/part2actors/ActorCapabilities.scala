package com.usver
package part2actors

import akka.actor.{Actor, ActorSystem, Props}
import akka.event.Logging

object ActorCapabilities extends App {

  val actorSystem = ActorSystem("capabilitySystem")

  val simpleActor = actorSystem.actorOf(Props[SimpleActor],"simpleActor1")

  // sending messages
  simpleActor ! "something"
  simpleActor ! 5
  simpleActor ! SpecialMessage("tada")
}

class SimpleActor extends Actor {
  val log = Logging(context.system, this)
  override def receive: Receive = {
    case message: String => println(s"[simple actor] received message:'$message'")
    case int: Int => println(s"[simple actor] INT value arrived: $int")
    // one way to handle whole object
    //case specialMessage: SpecialMessage =>
    // alternative way to handle only the essential parts
    case SpecialMessage(contents) => println(s"[simple actor] Got special message: '$contents'")
  }
}



case class SpecialMessage(contents: String)
