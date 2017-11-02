package com.jantox.joker

trait Bifunctor[F[_, _]] {

  def bimap[A, B, C, D](f: A => B)(g: C => D)(a: F[A, C]): F[B, D]

}

object Bifunctor {

  object EitherBifunctor extends Bifunctor[Either] {

    override def bimap[A, B, C, D](f: (A) => B)(g: (C) => D)(a: Either[A, C]): Either[B, D] = a match {
      case Right(r) => Right(g(r))
      case Left(l) => Left(f(l))
    }

  }

  object Tuple2Bifunctor extends Bifunctor[Tuple2] {

    override def bimap[A, B, C, D](f: (A) => B)(g: (C) => D)(a: (A, C)): (B, D) = {
      (f(a._1), g(a._2))
    }

  }

}
