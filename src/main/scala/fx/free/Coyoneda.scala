package fx.free

import fx.{Functor, ~>}

// Coyoneda is the free functor over F. It is a functor even when F is not.
// https://hackage.haskell.org/package/kan-extensions-5.0.2/docs/src/Data.Functor.Coyoneda.html#Coyoneda
// Conceptually, Coyoneda enables us to perform map fusion over some F[A]. We can lift F[A]
// onto Coyoneda[F[_], A] and perform `map` operations (which just accumulates composition),
// and when we lower the resulting Coyoneda[F[_], B], it applies the fused function to the original F[A],
// producing some F[B]. To lower the Coyoneda, F needs to have a corresponding Functor[F] over it.
trait Coyoneda[F[_], A] { self =>
  type Rep // The existential type that encodes the initial type

  def fs: F[Rep]
  def k: Rep => A

  // map is just function composition.
  def map[B](f: A => B): Coyoneda[F, B] = new Coyoneda[F, B] {
    override type Rep = self.Rep

    override def fs: F[Rep] = self.fs
    override def k: Rep => B = self.k.andThen(f)
  }

  def mapK[G[_]](f: F ~> G): Coyoneda[G, A] = new Coyoneda[G, A] {
    override type Rep = self.Rep

    override def fs: G[Rep] = f.apply(self.fs)
    override def k: Rep => A = self.k
  }
}

object Coyoneda {

  type Aux[F[_], R, A] = Coyoneda[F, A] { type Rep = R }

  // Yoneda expansion
  def liftCoyoneda[F[_], A](fa: F[A]): Coyoneda[F, A] = new Coyoneda[F, A] {
    override type Rep = A

    override def fs: F[Rep] = fa
    override def k: Rep => A = a => a
  }

  // Yoneda reduction. This is the only place where F is required to be a functor.
  // If F is a functor, Coyoneda[F, A] is isomorphic to F[A]
  def lowerCoyoneda[F[_]: Functor, A](coyo: Coyoneda[F, A]): F[A] = {
    implicitly[Functor[F]].map(coyo.k)(coyo.fs)
  }

  // hoistCoyoneda lifts a natural transformation F ~> G to Coyoneda[F, ?] -> Coyoneda[G, ?]
  def hoistCoyoneda[F[_], G[_], A](f: F ~> G)(coyo: Coyoneda[F, A]): Coyoneda[G, A] = {
    coyo.mapK(f)
  }

  implicit def coyonedaFunctor[F[_]]: Functor[Coyoneda[F, ?]] = new Functor[Coyoneda[F, ?]] {
    def map[A, B](f: A => B)(fa: Coyoneda[F, A]): Coyoneda[F, B] = {
      fa.map(f)
    }
  }

}
