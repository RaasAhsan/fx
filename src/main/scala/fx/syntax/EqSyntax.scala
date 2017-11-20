package fx.syntax

import fx.Eq

trait EqSyntax {

  implicit def eqOps[A: Eq](lhs: A): EqOps[A] = {
    new EqOps[A](lhs)
  }

}

class EqOps[A: Eq](lhs: A) {

  def ===(rhs: A): Boolean = implicitly[Eq[A]].eq(lhs, rhs)

}
