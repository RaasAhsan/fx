package fx

/**
  * Created by mahsan on 11/17/17.
  */
trait Contravariant[F[_]] {
  def cofmap[A, B](f: A => B)(b: F[B]): F[A]
}
