import sbt.*

object Dependencies {
  object Discord {
    val discord4J = "com.discord4j" % "discord4j-core" % "3.2.7"
    val all: Seq[ModuleID] = Seq(discord4J)
  }
  object Cats {
    val catsCore = "org.typelevel" %% "cats-core" % "2.13.0"
    val catsEffect = "org.typelevel" %% "cats-effect" % "3.5.7"
    val all: Seq[ModuleID] = Seq(catsCore, catsEffect)
  }
}
