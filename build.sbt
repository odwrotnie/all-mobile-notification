name := "All Mobile Notificator"

version := "1.0"

scalaVersion := "2.11.7"

val akkaStreamV = "2.0.3"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.6" % "test"

libraryDependencies += "com.typesafe.akka" %% "akka-http-core-experimental" % akkaStreamV

libraryDependencies += "com.typesafe.akka" %% "akka-http-spray-json-experimental" % akkaStreamV

libraryDependencies += "com.typesafe.akka" %% "akka-http-xml-experimental" % akkaStreamV

libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0"

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.2" % "runtime"

libraryDependencies += "com.typesafe.akka" %% "akka-slf4j" % "2.4.2"

libraryDependencies += "joda-time" % "joda-time" % "2.9.2"

libraryDependencies += "com.relayrides" % "pushy" % "0.5.2"

libraryDependencies += "com.notnoop.apns" % "apns" % "1.0.0.Beta6"
