package com.jantox.joker

trait Show[-A] {
  def show(a: A): String
}

object Show {

  val showString: Show[String] = new Show[String] {

    override def show(a: String): String = {
      a
    }

  }

  implicit object ShowContravariant extends Contravariant[Show] {
    override def cofmap[A, B](f: A => B)(s: Show[B]): Show[A] = new Show[A] {
      override def show(a: A): String = {
        s.show(f(a))
      }
    }
  }

}
