package fx.instances

import fx.{Eq, Show}
import fx.algebra.Monoid

trait StringInstances {

  implicit def showString: Show[String] = new Show[String] {
    override def show(a: String): String = a
  }

  implicit def monoidString: Monoid[String] = new Monoid[String] {
    override def zero: String = ""

    override def add(a: String, b: String): String = a + b
  }

  implicit def eqString: Eq[String] = new Eq[String] {
    override def eq(x: String, y: String): Boolean = x == y
  }

}
