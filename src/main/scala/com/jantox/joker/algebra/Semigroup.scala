package com.jantox.joker

trait Semigroup[S] {
  def add(a: S, b: S): S
}
