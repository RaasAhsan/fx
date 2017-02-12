package com.jantox.joker

import NaturalTransformation._

trait Modification[M1[_], M2[_], N1[_], N2[_]] {

  def apply(a: M1 ~> M2): (N1 ~> N2)

}

object Modification {

  type ~>>[M1[_], M2[_], N1[_], N2[_]] = Modification[M1, M2, N1, N2]

}
