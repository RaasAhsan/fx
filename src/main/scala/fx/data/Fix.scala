package fx.data

import fx.Functor

final case class Fix[F[_]](f: F[Fix[F]])

object Fix {

  // cata :: (f b -> b) -> Fix f -> b
  // cata z (Fix f) = z $ fmap (cata z) f
  def cata[F[_]: Functor, B](z: F[B] => B)(fix: Fix[F]): B = {
    z(implicitly[Functor[F]].map(cata(z))(fix.f))
  }

  // ana :: (b -> f b) -> b -> Fix f
  // ana z b = Fix $ fmap (ana z) (z b)
  def ana[F[_]: Functor, B](z: B => F[B])(b: B): Fix[F] = {
    Fix[F](implicitly[Functor[F]].map(ana(z))(z(b)))
  }

}
