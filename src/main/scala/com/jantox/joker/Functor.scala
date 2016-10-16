package com.jantox.joker

trait Functor[F[_]] {
  def map[A, B](f: A => B)(a: F[A]): F[B]
}
