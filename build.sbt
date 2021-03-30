name := "ScalaBeginner"

version := "0.1"

scalaVersion := "2.13.5"

idePackagePrefix := Some("com.usver")

val http4sVersion = "0.21.7"
val catsVersion = "2.2.0"
val catsEffectVersion = "2.2.0"
val scalaTestVersion = "3.2.5"
val akkaVersion = "2.5.23"

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % catsVersion,
  "org.typelevel" %% "cats-effect" % catsEffectVersion,
  "org.http4s" %% "http4s-dsl" % http4sVersion,
  "org.http4s" %% "http4s-blaze-server" % http4sVersion,
  "org.http4s" %% "http4s-blaze-client" % http4sVersion,
  "org.scalatest" %% "scalatest" % scalaTestVersion,
  "com.typesafe.akka" %% "akka-actor" % "2.5.23",
  "com.typesafe.akka" %% "akka-testkit" % "2.5.23",
)