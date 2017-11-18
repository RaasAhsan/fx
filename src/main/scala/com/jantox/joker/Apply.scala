package com.jantox.joker

trait Apply[F[_]] extends Functor[F] {

  def ap[A, B](a: F[A])(f: F[A => B]): F[B]

}
