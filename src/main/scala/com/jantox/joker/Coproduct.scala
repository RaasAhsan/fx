package com.jantox.joker

sealed trait Coproduct[F[_], G[_], A]
final case class InL[F[_], G[_], A](l: F[A]) extends Coproduct[F, G, A]
final case class InR[F[_], G[_], A](r: G[A]) extends Coproduct[F, G, A]
