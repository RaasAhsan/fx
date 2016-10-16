package com.jantox.joker

trait Monad[M[_]] extends Functor[M] {
  def returnM[A](a: A): M[A]
  def flatMap[A, B](f: A => M[B])(ma: M[A]): M[B]

  def map[A, B](f: A => B)(fa: M[A]): M[B] = {
    flatMap[A, B](a => returnM(f(a)))(fa)
  }
}
