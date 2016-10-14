package com.jantox.joker

trait Functor[F[_]] {
  def fmap[A, B](f: A => B)(a: F[A]): F[B]
}
