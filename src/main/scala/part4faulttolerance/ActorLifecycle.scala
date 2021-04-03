package com.usver
package part4faulttolerance

import akka.actor.{Actor, ActorLogging, ActorSystem, Kill, PoisonPill, Props}

object ActorLifecycle extends App {
  val actorSystem = ActorSystem("actorSystem")
  val parent = actorSystem.actorOf(Props[SomeActor],"parent")

  parent ! StartChild("child")
  Thread.sleep(1000)
  parent ! PoisonPill
}
case class StartChild(name: String)

class SomeActor extends Actor with ActorLogging {
  override def preStart(): Unit = log.info(s"[$name] I am starting")
  override def aroundPostStop(): Unit = log.info(s"[$name] aroundPostStop")
  override def postStop(): Unit = log.debug(s"[$name] I have stopped")

  lazy val name = context.self.path.name
  override def receive: Receive = {
    case StartChild(name) => context.actorOf(Props[SomeActor], name)
  }
}
