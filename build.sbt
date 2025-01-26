import Dependencies.*

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.4"

lazy val root = (project in file("."))
  .settings(
    name := "kse-scala-discord",
    libraryDependencies ++=
      Discord.all ++ Logging.all ++ Cats.all ++ CPS.all
  )
