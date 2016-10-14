package com.jantox.joker

sealed trait Coproduct[F[_], G[_], A]
final case class InL[F[_], G[_], A](l: F[A]) extends Coproduct[F, G, A]
final case class InR[F[_], G[_], A](r: G[A]) extends Coproduct[F, G, A]

object Coproduct {

  implicit def coproductFunctor[F[_]: Functor, G[_]: Functor, A]() = new Functor[({type f[x] = Coproduct[F, G, x]})#f] {
    def fmap[A, B](f: A => B)(fa: Coproduct[F, G, A]): Coproduct[F, G, B] = fa match {
      case InL(l) => InL(implicitly[Functor[F]].fmap(f)(l))
      case InR(r) => InR(implicitly[Functor[G]].fmap(f)(r))
    }
  }

}
