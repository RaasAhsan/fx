package fx

/**
  * Created by mahsan on 11/17/17.
  */
trait Apply[F[_]] extends Functor[F] {

  def ap[A, B](a: F[A])(f: F[A => B]): F[B]

}
