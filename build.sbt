val ScalatraVersion = "2.6.3"

organization := "com.example"

name := "My Scalatra Web App"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.12.4"

resolvers += Classpaths.typesafeReleases

libraryDependencies ++= Seq(
  "org.scalatra" %% "scalatra" % ScalatraVersion,
  "org.scalatra" %% "scalatra-scalatest" % ScalatraVersion % "test",
  "ch.qos.logback" % "logback-classic" % "1.2.3" % "runtime",
  "org.eclipse.jetty" % "jetty-webapp" % "9.4.9.v20180320" % "container",
  "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided",
  "org.scalikejdbc"  %% "scalikejdbc"  % "3.2.+",
  "org.slf4j"        %  "slf4j-simple" % "1.7.+",
  "com.h2database"   %  "h2"           % "1.4.+",
  "org.scalatest"    %% "scalatest"    % "3.0.+" % "test",
  "joda-time" % "joda-time" % "2.3",
  "org.joda" % "joda-convert" % "1.6"
)

val circeVersion = "0.9.3"
libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser"
).map(_ % circeVersion)

enablePlugins(SbtTwirl)
enablePlugins(ScalatraPlugin)
