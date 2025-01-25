package org.example.discord

import com.typesafe.scalalogging.StrictLogging
import discord4j.core.`object`.entity.{Message, User}
import discord4j.core.event.ReactiveEventAdapter
import discord4j.core.event.domain.message.MessageCreateEvent
import reactor.core.publisher.Mono
import scala.jdk.OptionConverters.*

object ReactiveEventAdapterLike extends ReactiveEventAdapter with StrictLogging:

  override def onMessageCreate(event: MessageCreateEvent): Mono[Unit] = echo(event.getMessage)
    .doOnError(error => logger.error(error.getMessage))

  private def echo(message: Message): Mono[Unit] = for {
    self    <- message.getClient.getSelf
    _       <- authorIsNotBot(self)(message.getAuthor.toScala)
    channel <- message.getChannel
    message <- channel.createMessage(message.getContent)
  } yield ()

  private def authorIsNotBot(self: User)(author: Option[User]): Mono[Unit] =
    Mono.justOrEmpty((author match
      case None => Some(())
      case some => some.filter(_ != self).map(_ => ())
    ).toJava)
