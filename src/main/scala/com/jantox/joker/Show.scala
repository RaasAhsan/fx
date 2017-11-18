package com.jantox.joker

trait Show[-A] { self =>

  def show(a: A): String

  def cofmap[B](f: B => A): Show[B] = new Show[B] {

    override def show(b: B): String = {
      self.show(f(b))
    }

  }

}

object Show {

  val showString: Show[String] = new Show[String] {

    override def show(a: String): String = {
      a
    }

  }

  implicit object ShowContravariant extends Contravariant[Show] {
    override def cofmap[A, B](f: A => B)(s: Show[B]): Show[A] = {
      s.cofmap(f)
    }
  }

}
