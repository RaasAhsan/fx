package com.jantox.joker

trait Profunctor[F[_, _]] {

  def profmap[A, B, C, D](f: A => B)(g: C => D)(a: F[A, D]): F[B, C]

}
