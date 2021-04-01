name := "ScalaBeginner"

version := "0.1"

scalaVersion := "2.13.5"

idePackagePrefix := Some("com.usver")

val http4sVersion = "0.21.7"
val catsVersion = "2.2.0"
val catsEffectVersion = "2.2.0"
val scalaTestVersion = "3.2.5"
val AkkaVersion = "2.6.8"
val AkkaHttpVersion = "10.2.4"

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % catsVersion,
  "org.typelevel" %% "cats-effect" % catsEffectVersion,
  "org.http4s" %% "http4s-dsl" % http4sVersion,
  "org.http4s" %% "http4s-blaze-server" % http4sVersion,
  "org.http4s" %% "http4s-blaze-client" % http4sVersion,
  "org.scalatest" %% "scalatest" % scalaTestVersion,

  "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
  "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
  "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,


  /*"com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream-typed" % akkaVersion,
  "com.typesafe.akka" %% "akka-http-core" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % Test,
  "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion % Test,*/

  //"com.typesafe.akka" %% "akka-testkit" % "2.5.3",
  //"com.typesafe.akka" %% "akka-http" % "10.2.4",
  //"com.typesafe.akka" %% "akka-stream" % "2.5.23",

)