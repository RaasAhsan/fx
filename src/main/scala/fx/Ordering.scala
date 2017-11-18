package fx

trait Ordering[A] extends Eq[A] { self =>
  def eq(a: A, b: A): Boolean
  def lt(a: A, b: A): Boolean
  def lte(a: A, b: A): Boolean
  def gt(a: A, b: A): Boolean
  def gte(a: A, b: A): Boolean

  def cofmap[B](f: B => A): Ordering[B] = new Ordering[B] {
    override def eq(a: B, b: B): Boolean = {
      self.eq(f(a), f(b))
    }

    override def lte(a: B, b: B): Boolean = {
      self.lte(f(a), f(b))
    }

    override def gt(a: B, b: B): Boolean = {
      self.gt(f(a), f(b))
    }

    override def lt(a: B, b: B): Boolean = {
      self.lt(f(a), f(b))
    }

    override def gte(a: B, b: B): Boolean = {
      self.gte(f(a), f(b))
    }
  }
}

object Ordering {

  implicit object OrderingContravariant extends Contravariant[Ordering] {
    override def cofmap[A, B](f: A => B)(o: Ordering[B]): Ordering[A] = {
      o.cofmap(f)
    }
  }

}
