package com.jantox.joker

trait Monad[F[_]] extends Applicative[F] {
  
  def point[A](a: A): F[A]
  
  def flatMap[A, B](a: F[A])(f: A => F[B]): F[B]

  override def pure[A](a: A): F[A] = {
    point(a)
  }

  override def ap[A, B](a: F[A])(f: F[A => B]): F[B] = {
    flatMap(a)(va => flatMap(f)(vf => point(vf(va))))
  }

}
