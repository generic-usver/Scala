package com.usver
package com.usver.akkaplayground

import akka.actor.ActorSystem

object AkkaPlayground extends App {

  val actorSystem = ActorSystem("HelloAkka")
  println(s"ActorSystem.name: ${actorSystem.name}")
}
