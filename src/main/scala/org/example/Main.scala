package org.example

import discord4j.core.DiscordClient
import org.example.discord.ReactiveEventAdapterLike

object Main:

  def main(args: Array[String]): Unit =
    val token = sys.env
      .get("token")
      .orElse(args.headOption)
      .fold(throw new IllegalArgumentException("Missing token"))(identity)
    DiscordClient
      .create(token)
      .withGateway(_.on(ReactiveEventAdapterLike))
      .block
