ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.4"

lazy val root = (project in file("."))
  .settings(
    name := "kse-scala-discord",
    libraryDependencies ++= Seq(
      "com.discord4j"               % "discord4j-core"  % "3.2.7",
      "ch.qos.logback"              % "logback-classic" % "1.5.16",
      "com.typesafe.scala-logging" %% "scala-logging"   % "3.9.5",
      "org.typelevel"              %% "cats-core"       % "2.13.0",
      "org.typelevel"              %% "mouse"           % "1.3.2",
      "io.github.dotty-cps-async"  %% "dotty-cps-async" % "1.0.0"
    )
  )
