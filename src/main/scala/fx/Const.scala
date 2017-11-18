package fx

final case class Const[A, B](a: A) {

  def map[C](f: B => C): Const[A, C] = {
    Const(a)
  }

}

object Const {

  implicit def constFunctor[A] = new Functor[Const[A, ?]] {
    override def map[B, C](f: B => C)(a: Const[A, B]): Const[A, C] = {
      a.map(f)
    }
  }

}
