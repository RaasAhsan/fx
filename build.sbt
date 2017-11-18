import sbt.Keys._

lazy val fx = (project in file("."))
  .settings(
    name := "fx",
    organization := "com.webdation",
    version := "0.1.0",

    scalaVersion := "2.12.3",
    scalacOptions ++= Seq(
      "-deprecation",
      "-feature",
      "-language:higherKinds",
      "-language:implicitConversions",
      "-unchecked"
    ),

    scalacOptions in Test ++= Seq("-Yrangepos"),

    libraryDependencies ++= Seq(
      compilerPlugin("org.spire-math" %% "kind-projector" % "0.9.4")
    )
  )

lazy val jokeson = (project in file("./jokeson"))
  .settings(
    name := "joker",
    organization := "com.webdation",
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
    name := "fx-examples",
    organization := "com.webdation",
    version := "0.1.0",

    scalaVersion := "2.12.3",
    scalacOptions += "-deprecation",

    scalacOptions in Test ++= Seq("-Yrangepos")
  )
  .dependsOn(fx)
