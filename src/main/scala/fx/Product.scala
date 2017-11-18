package fx

final case class Product[F[_], G[_], A](f: F[A], g: G[A]) {

  def map[B](z: A => B)(implicit F: Functor[F], G: Functor[G]): Product[F, G, B] = {
    Product(F.map(z)(f), G.map(z)(g))
  }

}

object Product {

  implicit def productFunctor[F[_]: Functor, G[_]: Functor]() = new Functor[Product[F, G, ?]] {
    override def map[A, B](f: A => B)(fa: Product[F, G, A]): Product[F, G, B] = {
      fa.map(f)
    }
  }

}
