package com.jantox.joker

trait Cofunctor[F[_]] {
  def cofmap[A, B](f: A => B)(b: F[B]): F[A]
}
