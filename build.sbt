import BuildHelper.*
import sbt.Keys.libraryDependencies

import scala.collection.Seq

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.1"

lazy val root = (project in file("."))
  .settings(
    name := "zio-training"
  )
  .aggregate(config, configApp, loggingApp)


lazy val config = (project in file("config"))
  .settings(
    name := "zio-config",
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio" % "2.0.16",
      "dev.zio" %% "zio-config" % "4.0.0-RC16",
      "dev.zio" %% "zio-config-magnolia" % "4.0.0-RC16",
      "dev.zio" %% "zio-config-typesafe" % "4.0.0-RC16",
      //      "dev.zio" %% "zio-config-refined"  % "4.0.0-RC5",
    )
  )

lazy val configApp = (project in file("config_app"))
  .settings(
    name := "zio-config-app"
  )
  .dependsOn(config)

lazy val loggingApp = (project in file("logging_app"))
  .settings(
    name := "zio-logging-app",
    libraryDependencies ++= Seq(
      "org.slf4j" % "slf4j-simple" % "2.0.5",
      "dev.zio" %% "zio-logging"       % "2.1.12",
      "dev.zio" %% "zio-logging-slf4j" % "2.1.12"
    )
  )
  .dependsOn(config, configApp)
