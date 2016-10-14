package com.jantox.joker

sealed trait Free[F[_], A]
final case class Wrap[F[_], A](f: F[Free[F, A]]) extends Free[F, A]
final case class Return[F[_], A](a: A) extends Free[F, A]

object Free {

  implicit def freeFunctor[F[_]: Functor]() = new Functor[({type f[x] = Free[F, x]})#f] {
    def fmap[A, B](f: A => B)(fa: Free[F, A]): Free[F, B] = fa match {
      case Wrap(wrapped) => Wrap(implicitly[Functor[F]].fmap[Free[F, A], Free[F, B]](freeA => fmap(f)(freeA))(wrapped))
      case Return(a) => Return(f(a))
    }
  }

}
