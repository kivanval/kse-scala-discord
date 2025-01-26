package org.example.cps

import cps.{CpsMonadConversion, CpsTryMonadInstanceContext}
import reactor.core.publisher.Mono

import java.util.Optional
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

given CpsMonadConversion[Optional, Mono] with {
  override def apply[T](ft: Optional[T]): Mono[T] = Mono.justOrEmpty(ft)
}
