package com.jantox.joker

trait NaturalTransformation[F[_], G[_]] {
  def apply[A](f: F[A]): G[A]
}
