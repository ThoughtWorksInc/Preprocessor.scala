organization := "com.thoughtworks.enableIf"

name := "enableIf"

addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)

libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.0" % Test

testFrameworks += new TestFramework("utest.runner.Framework")

libraryDependencies ++= {
  if (scalaBinaryVersion.value == "2.10") {
    Seq("org.scalamacros" %% "quasiquotes" % "2.1.0")
  } else {
    Seq()
  }
}

releaseCrossBuild := true

crossScalaVersions := Seq(
  "2.10.6",
  "2.11.8"
  // Don't build on 2.12.0-RC1 because ScalaTags did not support 2.12.0-RC1 yet
  // "2.12.0-RC1"
)

releasePublishArtifactsAction := PgpKeys.publishSigned.value

homepage := Some(url(raw"""https://github.com/ThoughtWorksInc/${name.value}"""))

startYear := Some(2016)

licenses := Seq("Apache License, Version 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.html"))

scmInfo := Some(ScmInfo(
  url(raw"""https://github.com/ThoughtWorksInc/${name.value}"""),
  raw"""scm:git:https://github.com/ThoughtWorksInc/${name.value}.git""",
  Some(raw"""scm:git:git@github.com:ThoughtWorksInc/${name.value}.git""")))

developers in ThisBuild := List(
  Developer(
    "Atry",
    "杨博 (Yang Bo)",
    "pop.atry@gmail.com",
    url("https://github.com/Atry")
  )
)

import ReleaseTransformations._

releaseProcess := {
  releaseProcess.value.patch(releaseProcess.value.indexOf(pushChanges), Seq[ReleaseStep](releaseStepCommand("sonatypeRelease")), 0)
}

releaseProcess -= runClean

releaseProcess -= runTest
