package com.usver
package part2actors

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
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
   */

  // 3 - Actors can REPLY to messages

  val alice = actorSystem.actorOf(Props[SimpleActor], "alice")
  val bob = actorSystem.actorOf(Props[SimpleActor], "bob")
  alice ! SayHiTo(bob) // pass Actor reference as a parameter

  // let's send a message with no Actor back reference
  alice ! "Hi!" // 4. Causes deadLetters as [sending Actor == null] @handling this type+reading context.sender

  // 5) Forwarding messages
  // D -> A -> B // forwarding == silent phones where we just pass message keeping the initial sender
  // This message type will be handled that way
  alice ! MessageWithTheOriginalSender("Well, let's just forward it", bob)
}

class SimpleActor extends Actor {
  val log = Logging(context.system, this)
  override def receive: Receive = {
    case "Hi!" => context.sender ! "Hello there!" // using context.sender() to reference sender
    case message: String => println(s"[${self.path.name}] received message:'$message'")
    case int: Int => println(s"[${self.path.name}] INT value arrived: $int")
    // one way to handle whole object
    //case specialMessage: SpecialMessage =>
    // alternative way to handle only the essential parts
    case SpecialMessage(contents) => println(s"[${self.path.name}] Got special message: '$contents'")
    case SendMessageToYourself(contents) =>
      self ! contents // we redirect the String as new message
    case SayHiTo(actorRef: ActorRef) =>
      actorRef ! "Hi!" // we pass message + OURSELVES (this) as the 2nd parameter, so we can be referenced later
    case MessageWithTheOriginalSender(contents, sender) =>
      sender forward contents + "!!" // forward == KEEP original SENDER
  }
}
case class SayHiTo(reference: ActorRef)
case class MessageWithTheOriginalSender(contents: String, reference: ActorRef)

case class SpecialMessage(contents: String)
case class SendMessageToYourself(contents: String)
