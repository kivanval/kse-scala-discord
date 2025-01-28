package org.example

import discord4j.core.{DiscordClient, GatewayDiscordClient}
import org.example.discord.ReactiveEventAdapterLike
import org.example.util.ResourceCommands

import scala.jdk.CollectionConverters.*

object Main:

  def main(args: Array[String]): Unit =
    val Array(rawToken, rawGuildId) = args
    val guildId                     = rawGuildId.toLong

    DiscordClient
      .create(rawToken)
      .withGateway { implicit gateway =>
        registerGuildCommands(guildId)
        gateway.on(ReactiveEventAdapterLike)
      }
      .block

  private def registerGuildCommands(guildId: Long)(using GatewayDiscordClient) =
    val client = summon[GatewayDiscordClient].getRestClient
    client.getApplicationId.flux
      .map(client.getApplicationService -> _)
      .flatMap(_.bulkOverwriteGuildApplicationCommand(_, guildId, ResourceCommands().asJava))
      .subscribe
