package com.jantox.joker

trait Contravariant[F[_]] {
  def cofmap[A, B](f: A => B)(b: F[B]): F[A]
}
