import sbt.*

object Dependencies {
  object Discord {
    val discord4J = "com.discord4j" % "discord4j-core" % "3.2.7"
    val all: Seq[ModuleID] = Seq(discord4J)
  }
  object Logging {
    val logback = "ch.qos.logback" % "logback-classic" % "1.5.16"
    val scalaLogging = "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5"
    val all: Seq[ModuleID] = Seq(logback, scalaLogging)
  }
  object Cats {
    val catsCore = "org.typelevel" %% "cats-core" % "2.13.0"
    val mouse = "org.typelevel" %% "mouse" % "1.3.2"
    val all: Seq[ModuleID] = Seq(catsCore, mouse)
  }
}
