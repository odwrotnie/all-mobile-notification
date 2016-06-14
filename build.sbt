name := "all-mobile-notifier"

version := "1.0"

scalaVersion := "2.11.7"

val akkaStreamV = "2.0.3"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.6" % "test"

libraryDependencies += "com.typesafe.akka" %% "akka-http-core-experimental" % akkaStreamV // Google Notifications

libraryDependencies += "com.typesafe.akka" %% "akka-http-spray-json-experimental" % akkaStreamV // Google Notifications

libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0"

libraryDependencies += "joda-time" % "joda-time" % "2.9.2"

libraryDependencies += "com.notnoop.apns" % "apns" % "1.0.0.Beta6" // iOS Notifications

libraryDependencies += "com.github.fernandospr" % "java-wns" % "1.3.1" // Windows Notifications

assemblyMergeStrategy in assembly := {
  case "META-INF/io.netty.versions.properties" => MergeStrategy.concat
  case x => (assemblyMergeStrategy in assembly).value(x) // The old one
}
