package com.jantox.joker

final case class Const[A, B](a: A)

object Const {

  implicit def constFunctor[T] = new Functor[({type f[x] = Const[T, x]})#f] {
    override def map[A, B](f: (A) => B)(a: Const[T, A]): Const[T, B] = {
      Const(a.a)
    }
  }
}
