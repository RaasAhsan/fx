package fx

trait Profunctor[F[_, _]] {

  def dimap[A, B, C, D](f: A => B)(g: C => D)(a: F[A, D]): F[B, C]

}
