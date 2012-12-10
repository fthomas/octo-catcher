name := "octo-catcher"

version := "0.0"

scalaVersion := "2.10.0-RC3"

resolvers ++= Seq(
    "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
    "spray repo" at "http://repo.spray.io"
)

libraryDependencies ++= Seq(
    "com.typesafe.akka" % "akka-actor_2.10.0-RC3" % "2.1.0-RC3",
    "io.spray" % "spray-client" % "1.1-M6",
    "io.spray" % "spray-json_2.10.0-RC3" % "1.2.3",
    "org.specs2" % "specs2_2.10.0-RC3" % "1.12.3"
)

scalacOptions += "-deprecation"
