package fx

/**
  * Created by mahsan on 11/17/17.
  */
trait Applicative[F[_]] extends Apply[F] {

  def pure[A](a: A): F[A]

  override def map[A, B](f: A => B)(a: F[A]): F[B] = {
    ap(a)(pure(f))
  }

  def map2[A, B, C](f: A => B => C)(a: F[A], b: F[B]): F[C] = {
    ap(b)(ap(a)(pure(f)))
  }

}
