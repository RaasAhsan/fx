package fx.syntax

import fx.algebra.Monoid

trait MonoidSyntax {

  implicit def monoidOps[A: Monoid](lhs: A): MonoidOps[A] = {
    new MonoidOps[A](lhs)
  }

}

class MonoidOps[A: Monoid](lhs: A) {

  def |+|(rhs: A): A = implicitly[Monoid[A]].add(lhs, rhs)

}
