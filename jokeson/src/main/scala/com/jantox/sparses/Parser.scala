package com.jantox.sparses

import shapeless.{::, Generic, HList, HNil}

import scala.util.Try

trait Parser[A] { self =>

  def parse(input: String): Result[A]

  def map[B](f: A => B): Parser[B] = new Parser[B] {

    override def parse(input: String): Result[B] = {
      self.parse(input).map(f)
    }

  }

}

object Parser {

  implicit def stringParser: Parser[String] = new Parser[String] {

    override def parse(input: String) = {
      Right(input)
    }

  }

  implicit def intParser: Parser[Int] = new Parser[Int] {

    override def parse(input: String): Result[Int] = {
      Try(input.toInt).toEither.left.map(_ => "Failed to parse int.")
    }

  }

  implicit def hnilParser: Parser[HNil] = new Parser[HNil] {

    override def parse(input: String): Result[HNil] = {
      if (input.isEmpty) Right(HNil) else Left("Row not empty.")
    }

  }

  // We can write a Parser for a type H :: T, where T <: HList if we have Parser[H], Parser[T].
  implicit def hconsParser[H: Parser, T <: HList: Parser] = new Parser[H :: T] {

    override def parse(input: String): Result[H :: T] = {
      input.split(",").toList match {
        case head +: tail => for {
          pHead <- implicitly[Parser[H]].parse(head)
          pTail <- implicitly[Parser[T]].parse(tail.mkString(","))
        } yield pHead :: pTail
      }
    }

  }

  def derive[A](implicit generic: Generic[A]): Parser[A] = {
    genericParser(Generic.Aux[A, generic.Repr], implicitly[Parser[generic.Repr]])
  }

  implicit def genericParser[A, R <: HList](implicit generic: Generic.Aux[A, R], parser: Parser[R]): Parser[A] = {
    parser.map(generic.from)
  }

}
