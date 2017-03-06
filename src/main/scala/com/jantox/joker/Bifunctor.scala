package com.jantox.joker

trait Bifunctor[F[_, _]] {

  def bifmap[A, B, C, D](f: A => B)(g: C => D)(a: F[A, C]): F[B, D]

}
