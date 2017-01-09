package com.jantox.joker

trait Ordering[O] extends Eq[O] {
  def eq(a: O, b: O): Boolean
  def lt(a: O, b: O): Boolean
  def lte(a: O, b: O): Boolean
  def gt(a: O, b: O): Boolean
  def gte(a: O, b: O): Boolean
}

object Ordering {

  implicit object OrderingCofunctor extends Cofunctor[Ordering] {
    override def cofmap[A, B](f: A => B)(o: Ordering[B]): Ordering[A] = new Ordering[A] {
      override def eq(a: A, b: A): Boolean = {
        o.eq(f(a), f(b))
      }

      override def lte(a: A, b: A): Boolean = {
        o.lte(f(a), f(b))
      }

      override def gt(a: A, b: A): Boolean = {
        o.gt(f(a), f(b))
      }

      override def lt(a: A, b: A): Boolean = {
        o.lt(f(a), f(b))
      }

      override def gte(a: A, b: A): Boolean = {
        o.gte(f(a), f(b))
      }
    }
  }

}
