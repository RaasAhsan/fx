package fx.free

import fx.{Functor, Monad}

// This datatype encodes Free with the Coyoneda semantics directly embedded within it.
sealed trait Free2[F[_], A] { self =>

  import Free2._

  def map[B](f: A => B): Free2[F, B] = {
    flatMap(a => Pure(f(a)))
  }

  def flatMap[B](f: A => Free2[F, B]): Free2[F, B] = {
    FlatMap(self, f)
  }

}

object Free2 {

  // Reification of the flatMap operation into a data constructor.
  final case class FlatMap[F[_], A, B](freeA: Free2[F, A], f: A => Free2[F, B]) extends Free2[F, B]
  final case class Suspend[F[_], A](f: F[A]) extends Free2[F, A]
  final case class Pure[F[_], A](a: A) extends Free2[F, A]

  def liftF[F[_], A](fa: F[A]): Free2[F, A] = {
    Suspend(fa)
  }

  implicit def freeFunctor[F[_]] = new Functor[Free2[F, ?]] {
    def map[A, B](f: A => B)(fa: Free2[F, A]): Free2[F, B] = {
      fa.map(f)
    }
  }

  implicit def freeMonad[F[_]] = new Monad[Free2[F, ?]] {
    override def point[A](a: A): Free2[F, A] = Pure(a)

    override def flatMap[A, B](fa: Free2[F, A])(f: A => Free2[F, B]): Free2[F, B] = {
      fa.flatMap(f)
    }
  }

}
