package org.example.discord

import com.typesafe.scalalogging.StrictLogging
import cps.{*, given}
import org.example.cps.{*, given}
import discord4j.core.`object`.entity.{Message, User}
import discord4j.core.event.ReactiveEventAdapter
import discord4j.core.event.domain.message.MessageCreateEvent
import reactor.core.publisher.Mono

import scala.jdk.OptionConverters.*

object ReactiveEventAdapterLike extends ReactiveEventAdapter with StrictLogging:

  override def onMessageCreate(event: MessageCreateEvent): Mono[Unit] = echo(event.getMessage)
    .doOnError(error => logger.error(error.getMessage))

  private def echo(message: Message): Mono[Unit] = async[Mono] {
    val self   = await(message.getClient.getSelf)
    val author = message.getAuthor.toScala
    if (!author.contains(self)) {
      val channel = await(message.getChannel)
      await[Mono, Message, Mono](channel.createMessage(message.getContent))
    }
  }
