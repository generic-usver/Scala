package com.usver
package part4faulttolerance

import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, PoisonPill, Props}
import Parent._

object StartStopActors extends App {
  val actorSystem = ActorSystem("start-stop-exercise")
  val parentActor = actorSystem.actorOf(Props[Parent], "parent")
  parentActor ! StartChild("child1")

  // we can use parentActorSystem.actorSelection($PATH) to fetch reference
  val child1 = actorSystem.actorSelection("/user/parent/child1")
  child1 ! "Some test message!"

  // operating with the 2nd child
  parentActor ! StartChild("child2")
  val child2 = actorSystem.actorSelection("/user/parent/child2")

  //parentActor ! StopParent
  parentActor ! PoisonPill
  for (i <- 1 to 100) parentActor ! s"$i Parent, Are you still there?"

  //child2 ! "Some test message! - should be OK"
  //parentActor ! StopChild("child2")
  for (i <- 1 to 100) child2 ! s"$i Child2, Are you still there?"
  // stopping all children
}

object Parent {
  // data/case classes == POJOs for messages
  case class StartChild(name: String)
  case class StopChild(name: String)
  case object StopParent // parent stopping itself
}

class Parent extends Actor with ActorLogging {
  import Parent._ // companion object case classes
  // the mandatory method implemented elsewhere
  override def receive: Receive = withChildren(Map()) // Passing an empty Map() by default
  def showChildren(map: Map[String, Any], info: String = "") = {
    log.info(s" >> $info children: (${map.size}) => ${map.keys}")
  }

  // we stuff
  def withChildren(children: Map[String, ActorRef]): Receive = {
    case StartChild(name) => {
      //showChildren(children, s"before adding $name")
      log.info(s"[${self.path.name} :: starting new child $name")

      // New actors can be created by "context.actorOf().."
      // Maps are updated simply by "oldMap + (key -> value)"
      // Inner state can be changed by "context.become()"

      // That "context" sure plays a big role there ;)
      context.become(withChildren(children + (name -> context.actorOf(Props[Child], name))))
      //showChildren(children, s"after adding $name")
    }
    case StopChild(name) => {
      log.info(s"[${self.path.name} got a request to stop child $name")
      //showChildren(children, s"before removing $name")
      val childToStop = children get name

      // Braced way to do this
      //if (childToStop.isDefined) {
      //  context.stop(childToStop.get)
      //}

      // Alternative way to do this: use foreach:
      childToStop.foreach(realChildRef => context.stop(realChildRef))

      val childrenWithoutRemovable = children - name
      context.become(withChildren(childrenWithoutRemovable))
      showChildren(childrenWithoutRemovable, s"after removing $name")
    }
    case StopParent => context.stop(self)
    case message: String => log.info(s"[${self.path.name} got a String: $message")
    case int: Int => log.info(s"[${self.path.name} got an Int: $int")
    case unknown => log.info(s"[${self.path.name} got something else: $unknown")
  }
}

// a just-output name
class Child extends Actor with ActorLogging {
  override def preStart(): Unit = super.preStart(); log.info(s"  >> Starting a new child: ${self.path.name}")
  override def postStop(): Unit = super.postStop(); log.warning(s"  >> Stopping a child: ${self.path.name} <-- why is this happening??")

  override def receive: Receive = {
    case message => log.info(s"[${self.path.name} receives: $message")
  }
}

case class SimplestPOJO(name: String, count: Int)