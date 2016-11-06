import sbt.Keys._

lazy val root = (project in file("."))
  .settings(
    name := "joker",
    organization := "com.jantox",
    version := "0.1.0",

    scalaVersion := "2.11.8",
    scalacOptions += "-deprecation",

    scalacOptions in Test ++= Seq("-Yrangepos")
  )

lazy val examples = (project in file("./examples"))
  .settings(
    name := "joker-examples",
    organization := "com.jantox",
    version := "0.1.0",

    scalaVersion := "2.11.8",
    scalacOptions += "-deprecation",

    scalacOptions in Test ++= Seq("-Yrangepos")
  )
  .dependsOn(root)
