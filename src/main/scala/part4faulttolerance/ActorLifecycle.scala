package com.usver
package part4faulttolerance

import akka.actor.{Actor, ActorLogging, ActorSystem, Kill, PoisonPill, Props}

object ActorLifecycle extends App {
  val actorSystem = ActorSystem("actorSystem")
  val parent = actorSystem.actorOf(Props[SomeActor],"parent")

  // Triggering start/stop event listeners
  parent ! StartChild("child")
  Thread.sleep(1000)
  parent ! PoisonPill

  // Restart features

  val parentWithChild = actorSystem.actorOf(Props[SupervisingParent],"parentWithChild")
  parentWithChild ! FailChild
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

object FailObject
object FailChild

class SupervisingParent extends Actor with ActorLogging {
  val child = context.actorOf(Props[SupervisedChild], "supervisedChild")
  override def receive: Receive = {
    case FailChild => child ! FailObject
  }
}
class SupervisedChild extends Actor with ActorLogging {
  override def receive: Receive = {
    case FailObject => {
      log.warning("Child will fail now")
      throw new RuntimeException("I failed")
    };
  }

  override def preStart(): Unit = log.info("supervised child starting")
  override def postStop(): Unit = log.info("supervised child stopped")

  // Before replacing the Actor object
  override def preRestart(reason: Throwable, message: Option[Any]): Unit = {
    log.warning(s"Supervised actor restarting because ${reason.getMessage}, message: $message")
  }
  // After replacing the Actor object
  override def postRestart(reason: Throwable): Unit = {
    log.warning(s"Supervised child done restarting because: ${reason.getMessage}")
  }
}
