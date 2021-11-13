val ScalatraVersion = "2.8.2"

ThisBuild / scalaVersion := "2.13.7"
ThisBuild / organization := "org.myweather"

scalacOptions ++= Seq("-deprecation", "-feature")

lazy val hello = (project in file("."))
  .settings(
    name := "MyWeather",
    version := "0.1.0-SNAPSHOT",
    libraryDependencies ++= Seq(
      "org.scalatra" %% "scalatra" % ScalatraVersion,
      "ch.qos.logback" % "logback-classic" % "1.2.3" % "runtime",
      "org.eclipse.jetty" % "jetty-webapp" % "9.4.35.v20201120" % "container",
      "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided",
      "io.spray" %% "spray-json" % "1.3.6",

      "org.scalatra" %% "scalatra-scalatest" % ScalatraVersion % "test",
      "org.scalatest" %% "scalatest" % "3.2.10" % "test",
      "org.scalatestplus" %% "mockito-3-4" % "3.2.10.0" % "test",
    ),
  )

enablePlugins(JettyPlugin)
