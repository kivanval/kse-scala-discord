package org.example.cps

import cps.{CpsMonadConversion, CpsTryMonadInstanceContext}
import discord4j.core.spec.{MessageCreateMono, Spec}
import reactor.core.publisher.Mono

import scala.util.{Failure, Success, Try}

class MonoCpsMonad extends CpsTryMonadInstanceContext[Mono] {
  override def error[A](e: Throwable): Mono[A] = Mono.error(e)

  override def flatMapTry[A, B](fa: Mono[A])(f: Try[A] => Mono[B]): Mono[B] =
    fa.flatMap(a => f(Success(a)).onErrorResume(t => f(Failure(t))))

  override def pure[T](t: T): Mono[T] = Mono.just(t)

  override def map[A, B](fa: Mono[A])(f: A => B): Mono[B]           = fa.map(a => f(a))
  override def flatMap[A, B](fa: Mono[A])(f: A => Mono[B]): Mono[B] = fa.flatMap(a => f(a))
}

given MonoCpsMonad = new MonoCpsMonad


given CpsMonadConversion[Spec, Mono] with {
  def apply[T](ft:Spec[T]): Mono[T] = ???
}