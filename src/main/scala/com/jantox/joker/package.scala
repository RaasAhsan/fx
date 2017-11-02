package com.jantox

package object joker {

  import Id._

  import NaturalTransformation._

  def const[A, B](a: A)(b: B): A = {
    a
  }

}
