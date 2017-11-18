package com.jantox

package object joker {

  type Id[A] = A
  type ~>[F[_], G[_]] = NaturalTransformation[F, G]

  def const[A, B](a: A)(b: B): A = {
    a
  }

}
