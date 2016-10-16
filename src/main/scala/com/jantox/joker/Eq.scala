package com.jantox.joker

trait Eq[E] {
  def eq(a: E, b: E): Boolean
}
