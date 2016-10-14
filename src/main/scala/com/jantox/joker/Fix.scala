package com.jantox.joker

final case class Fix[F[_]](f: F[Fix[F]])
