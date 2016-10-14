package com.jantox.joker

trait Monoid[M] extends Semigroup[M] {
  def zero: M
  def add(a: M, b: M): M
}
