package com.jantox.joker

sealed trait Free[F[_], A]
final case class Wrap[F[_], A](f: F[Free[F, A]]) extends Free[F, A]
final case class Return[F[_], A](a: A) extends Free[F, A]

object Free {

  implicit def freeFunctor[F[_]: Functor]() = new Functor[({type f[x] = Free[F, x]})#f] {
    def fmap[A, B](f: A => B)(fa: Free[F, A]): Free[F, B] = fa match {
      case Wrap(wrapped) => Wrap(implicitly[Functor[F]].fmap((freeA: Free[F, A]) => fmap(f)(freeA))(wrapped))
      case Return(a) => Return(f(a))
    }
  }

  implicit def freeMonad[F[_]: Functor]() = new Monad[({type f[x] = Free[F, x]})#f] {
    override def returnM[A](a: A): Free[F, A] = ???

    override def bind[A, B](f: A => Free[F, B])(ma: Free[F, A]): Free[F, B] = ma match {
      case Wrap(wrapped) => Wrap(implicitly[Functor[F]].fmap((freeA: Free[F, A]) => bind(f)(freeA))(wrapped))
      case Return(a) => f(a)
    }
  }

}
