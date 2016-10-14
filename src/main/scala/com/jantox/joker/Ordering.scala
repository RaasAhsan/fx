package com.jantox.joker

trait Ordering[O] {
  def eq(a: O, b: O): Boolean
  def lt(a: O, b: O): Boolean
  def lte(a: O, b: O): Boolean
  def gt(a: O, b: O): Boolean
  def gte(a: O, b: O): Boolean
}
