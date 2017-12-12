package fx.free

import fx.{Functor, ~>}

sealed trait Free[F[_], A] { self =>

  import Free._

  def map[B](f: A => B)(implicit F: Functor[F]): Free[F, B] = self match {
    case Pure(a) => Pure(f(a))
    case Suspend(fa) => Suspend(F.map[Free[F, A], Free[F, B]](freeA => freeA.map(f))(fa))
  }

  def flatMap[B](f: A => Free[F, B])(implicit F: Functor[F]): Free[F, B] = self match {
    case Pure(a) => f(a)
    case Suspend(fa) => Suspend(F.map[Free[F, A], Free[F, B]](freeA => freeA.flatMap(f))(fa))
  }

}

object Free {

  final case class Pure[F[_], A](a: A) extends Free[F, A]
  final case class Suspend[F[_], A](fa: F[Free[F, A]]) extends Free[F, A]

  // Coyoneda is a functor for any F, so FreeC is both a functor and monad for any F.
  type FreeC[F[_], A] = Free[Coyoneda[F, ?], A]

  def liftFC[F[_], A](fa: F[A]): FreeC[F, A] = {
    val coyoFa = Coyoneda.liftCoyoneda(fa)

    Suspend[Coyoneda[F, ?], A](coyoFa.map(a => Pure[Coyoneda[F, ?], A](a)))
  }

}
