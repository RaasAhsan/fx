package fx

trait Eq[-A] {

  def eq(x: A, y: A): Boolean

}

object Eq {

  implicit object EqContravariant extends Contravariant[Eq] {

    override def cofmap[A, B](f: (A) => B)(b: Eq[B]): Eq[A] = new Eq[A] {

      override def eq(x: A, y: A): Boolean = {
        b.eq(f(x), f(y))
      }

    }

  }

}
