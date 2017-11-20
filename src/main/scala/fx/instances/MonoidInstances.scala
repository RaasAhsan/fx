package fx.instances

import fx.algebra.Monoid

trait MonoidInstances {

  implicit def intMonoid: Monoid[Int] = new Monoid[Int] {

    override def zero: Int = 0

    override def add(a: Int, b: Int): Int = {
      a + b
    }

  }

  implicit def listMonoid[A]: Monoid[List[A]] = new Monoid[List[A]] {

    override def zero: List[A] = List()

    override def add(a: List[A], b: List[A]): List[A] = {
      a ++ b
    }

  }

  implicit def vectorMonoid[A]: Monoid[Vector[A]] = new Monoid[Vector[A]] {

    override def zero: Vector[A] = Vector()

    override def add(a: Vector[A], b: Vector[A]): Vector[A] = {
      a ++ b
    }

  }

}
