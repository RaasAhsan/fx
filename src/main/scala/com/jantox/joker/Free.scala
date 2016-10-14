package com.jantox.joker

sealed trait Free[F[_], A]
final case class Wrap[F[_], A](f: F[Free[F, A]]) extends Free[F, A]
final case class Return[F[_], A](a: A) extends Free[F, A]
