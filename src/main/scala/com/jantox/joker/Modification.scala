package com.jantox.joker

import NaturalTransformation._

trait Modification[M1[_], M2[_], N1[_], N2[_]] {

  def apply[A](a: M1[A] ~> M2[A]): (N1[A] ~> N2[A])

}

object Modification {

  type ~>>[M1[_], M2[_], N1[_], N2[_]] = Modification[M1, M2, N1, N2]

}
