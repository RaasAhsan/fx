package com.jantox.joker

trait Applicative[F[_]] extends Functor[F] {

  def pure[A](a: A): F[A]

  def ap[A, B](a: F[A])(f: F[A => B]): F[B]

  override def map[A, B](f: A => B)(a: F[A]): F[B] = {
    ap(a)(pure(f))
  }

  def map2[A, B, C](f: A => B => C)(a: F[A], b: F[B]): F[C] = {
    ap(b)(ap(a)(pure(f)))
  }

}
