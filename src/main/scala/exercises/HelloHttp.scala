package com.usver
package exercises

import cats.effect._
import cats.effect.{ExitCode, IO, IOApp}
import fs2.Pipe
import fs2.concurrent.Queue
import org.http4s.server.blaze.BlazeServerBuilder
import org.http4s.server.websocket.WebSocketBuilder
import org.http4s.websocket.WebSocketFrame

import scala.concurrent.ExecutionContext

// import cats.effect._

import org.http4s._
import org.http4s.dsl.io._
import org.http4s.implicits._


object WebSocketServer extends IOApp {
  private val webSocketRoute = HttpRoutes.of[IO] {

    // Testing: websocat "ws://127.0.0.1:9002/echo
    case GET -> Root / "echo" =>
      // Pipe is a stream transformation function of type `Stream[F, I] => Stream[F, O]`. In this case
      // `I == O == WebSocketFrame`. So the pipe transforms incoming WebSocket messages from the client to
      // outgoing WebSocket messages to send to the client.
      val echoPipe: Pipe[IO, WebSocketFrame, WebSocketFrame] =
        _.collect {
          case WebSocketFrame.Text(message, _) => {
            println(s"Incoming message: '$message'")
            WebSocketFrame.Text(s"Answering something to $message")
          }
        }

      for {
        // Unbounded queue to store WebSocket messages from the client, which are pending to be processed.
        // For production use bounded queue seems a better choice. Unbounded queue may result in out of
        // memory error, if the client is sending messages quicker than the server can process them.
        queue <- Queue.unbounded[IO, WebSocketFrame]
        response <- WebSocketBuilder[IO].build(
          // Sink, where the incoming WebSocket messages from the client are pushed to.
          receive = queue.enqueue,
          // Outgoing stream of WebSocket messages to send to the client.
          send = queue.dequeue.through(echoPipe),
        )
      } yield response
  }

  override def run(args: List[String]): IO[ExitCode] =
    BlazeServerBuilder[IO](ExecutionContext.global)
      .bindHttp(port = 9002, host = "localhost")
      .withHttpApp(webSocketRoute.orNotFound)
      .serve
      .compile
      .drain
      .as(ExitCode.Success)
}
