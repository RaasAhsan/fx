package com.jantox.joker

sealed trait Free[F[_], A]

final case class WrapF[F[_], A](f: F[Free[F, A]]) extends Free[F, A]

final case class ReturnF[F[_], A](a: A) extends Free[F, A]

object Free {

  implicit def freeFunctor[F[_]: Functor]() = new Functor[({type f[x] = Free[F, x]})#f] {
    def map[A, B](f: A => B)(fa: Free[F, A]): Free[F, B] = fa match {
      case WrapF(wrapped) => WrapF(implicitly[Functor[F]].map((freeA: Free[F, A]) => map(f)(freeA))(wrapped))
      case ReturnF(a) => ReturnF(f(a))
    }
  }

  implicit def freeMonad[F[_]: Functor]() = new Monad[({type f[x] = Free[F, x]})#f] {
    override def returnM[A](a: A): Free[F, A] = ???

    override def flatMap[A, B](f: A => Free[F, B])(ma: Free[F, A]): Free[F, B] = ma match {
      case WrapF(wrapped) => WrapF(implicitly[Functor[F]].map((freeA: Free[F, A]) => flatMap(f)(freeA))(wrapped))
      case ReturnF(a) => f(a)
    }
  }

}
