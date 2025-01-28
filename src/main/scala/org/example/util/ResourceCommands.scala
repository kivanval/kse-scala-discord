package org.example.util

import discord4j.discordjson.json.ApplicationCommandRequest

import java.nio.file.{Files, Paths}
import scala.jdk.CollectionConverters.*

object ResourceCommands:
  private val DefaultDir = "/commands"

  def apply(dirName: String = DefaultDir): List[ApplicationCommandRequest] =
    Option(getClass.getResource(dirName))
      .map(url => Paths.get(url.toURI))
      .filter(path => Files.isDirectory(path))
      .toList
      .flatMap(
        Files
          .list(_)
          .map(path => Mappers.discord4j.readValue(Files.readString(path), classOf[ApplicationCommandRequest]))
          .toList
          .asScala
          .toList
      )
