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

  // send to myself
  simpleActor ! SendMessageToYourself("message to myself")

  /*
    Messages can be any type AS LONG AS:
     1) They are IMMUTABLE
     2) They are SERIALIZABLE (Usually: case Classes or case Objects)
   */

  /*
    Actors know the environment they are in
     1) ActorClass -> context.self == "this" very actor
      actor.self.path == akka:// protocol address
     2) We can use that context.self to send messages to ourselves (as Actor)
      // context.self == this == self
     3)
   */
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
    case SendMessageToYourself(contents) =>
      self ! contents // we redirect the String as new message
  }
}



case class SpecialMessage(contents: String)
case class SendMessageToYourself(contents: String)
