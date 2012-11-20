name := "octo-catcher"

version := "0.0"

scalaVersion := "2.10.0-RC2"

resolvers ++= Seq(
    "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
    "spray repo" at "http://repo.spray.io"
)

libraryDependencies ++= Seq(
    "com.typesafe.akka" % "akka-actor_2.10.0-RC2" % "2.1.0-RC2",
    "io.spray" % "spray-client" % "1.1-M5",
    "org.specs2" % "specs2_2.10.0-RC2" % "1.12.2"
)

scalacOptions += "-deprecation"
