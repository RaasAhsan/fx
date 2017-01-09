package com.jantox.joker

sealed trait Trampoline[T]

final case class ReturnT[T](value: T) extends Trampoline[T]

final case class JumpT[T](f: () => Trampoline[T]) extends Trampoline[T]

object Trampoline {

  // Trampolines allow us to delay the evaluation of a potentially
  // recursive or mutually recursive computation so it be evaluated
  // on a common function `run`.

  @annotation.tailrec
  def run[T](t: Trampoline[T]): T = t match {
    case ReturnT(value) => value
    case JumpT(f) => run(f())
  }

}
