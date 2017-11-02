import sbt.Keys._

lazy val root = (project in file("."))
  .settings(
    name := "joker",
    organization := "com.jantox",
    version := "0.1.0",

    scalaVersion := "2.12.3",
    scalacOptions += "-deprecation",

    scalacOptions in Test ++= Seq("-Yrangepos")
  )

lazy val jokeson = (project in file("./jokeson"))
  .settings(
    name := "joker",
    organization := "com.jantox",
    version := "0.1.0",

    scalaVersion := "2.12.3",
    scalacOptions += "-deprecation",

    scalacOptions in Test ++= Seq("-Yrangepos"),

    libraryDependencies ++= Seq(
      "com.chuusai" %% "shapeless" % "2.3.2"
    )
  )

lazy val examples = (project in file("./examples"))
  .settings(
    name := "joker-examples",
    organization := "com.jantox",
    version := "0.1.0",

    scalaVersion := "2.12.3",
    scalacOptions += "-deprecation",

    scalacOptions in Test ++= Seq("-Yrangepos")
  )
  .dependsOn(root)
