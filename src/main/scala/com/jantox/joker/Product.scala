package com.jantox.joker

final case class Product[F[_], G[_], A](f: F[A], g: G[A])

object Product {

  implicit def productFunctor[F[_]: Functor, G[_]: Functor]() = new Functor[({type f[x] = Product[F, G, x]})#f] {
    def map[A, B](f: A => B)(fa: Product[F, G, A]): Product[F, G, B] = {
      Product(implicitly[Functor[F]].map(f)(fa.f), implicitly[Functor[G]].map(f)(fa.g))
    }
  }

}
