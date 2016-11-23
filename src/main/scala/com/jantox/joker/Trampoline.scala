package com.jantox.joker

sealed trait Trampoline[T]

final case class ReturnT[T](value: T) extends Trampoline[T]

final case class JumpT[T](f: () => Trampoline[T]) extends Trampoline[T]

object Trampoline {

  // Trampolines allow us to lift the type of a recursive function
  // onto a common Trampoline[T] type, which is where the recursion
  // return type can rest on, allowing for tail recursive functions
  // and therefore, stack safe functions.

  @annotation.tailrec
  def run[T](t: Trampoline[T]): T = t match {
    case ReturnT(value) => value
    case JumpT(f) => run(f())
  }

}
