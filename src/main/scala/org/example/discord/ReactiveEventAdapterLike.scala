package org.example.discord

import com.typesafe.scalalogging.StrictLogging
import discord4j.core.`object`.entity.{Message, User}
import discord4j.core.event.ReactiveEventAdapter
import discord4j.core.event.domain.message.MessageCreateEvent
import reactor.core.publisher.Mono

object ReactiveEventAdapterLike extends ReactiveEventAdapter with StrictLogging:

  override def onMessageCreate(event: MessageCreateEvent): Mono[Unit] = echo(event.getMessage)
    .doOnError(error => logger.error(error.getMessage))

  private def echo(message: Message): Mono[Unit] = for {
    self    <- message.getClient.getSelf
    _       <- Mono.justOrEmpty(message.getAuthor.filter(_ != self))
    channel <- message.getChannel
    message <- channel.createMessage(message.getContent)
  } yield ()
