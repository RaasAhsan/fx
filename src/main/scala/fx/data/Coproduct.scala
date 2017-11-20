package fx.data

import fx.Functor

sealed trait Coproduct[F[_], G[_], A] {
  def map[B](f: A => B)(implicit F: Functor[F], G: Functor[G]): Coproduct[F, G, B]
}

final case class InL[F[_], G[_], A](l: F[A]) extends Coproduct[F, G, A] {
  override def map[B](f: A => B)(implicit F: Functor[F], G: Functor[G]): Coproduct[F, G, B] = {
    InL[F, G, B](F.map(f)(l))
  }
}

final case class InR[F[_], G[_], A](r: G[A]) extends Coproduct[F, G, A] {
  override def map[B](f: A => B)(implicit F: Functor[F], G: Functor[G]): Coproduct[F, G, B] = {
    InR[F, G, B](G.map(f)(r))
  }
}

object Coproduct {

  implicit def coproductFunctor[F[_]: Functor, G[_]: Functor]() = new Functor[Coproduct[F, G, ?]] {
    def map[A, B](f: A => B)(fa: Coproduct[F, G, A]): Coproduct[F, G, B] = {
      fa.map(f)
    }
  }

}
