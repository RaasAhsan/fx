package com.jantox.joker

object FreeExamples {

  type Language[A] = Free[LanguageA, A]

  sealed trait LanguageA[A]
  final case class Alert[A](message: String, next: A) extends LanguageA[A]
  final case class Ding[A](n: Int, next: A) extends LanguageA[A]
  final case class Done[A]() extends LanguageA[A]

  implicit val languageFunctor = new Functor[LanguageA] {

    override def map[A, B](f: (A) => B)(a: LanguageA[A]): LanguageA[B] = {
      a match {
        case Alert(message, next) => Alert(message, f(next))
        case Ding(n, next) => Ding(n, f(next))
        case Done() => Done()
      }
    }

  }

  def alert(message: String): Language[Unit] = {
    Suspend(Alert(message, Pure(Done())))
  }

  def ding(n: Int): Language[Unit] = {
    Suspend(Ding(n, Pure(Done())))
  }

  def done(): Language[Unit] = {
    Suspend(Done())
  }

  def main(args: Array[String]): Unit = {
    val result = for {
      _ <- alert("hello!")
      _ <- ding(50)
      _ <- alert("world!")
    } yield ()

    println(result)
  }

}
