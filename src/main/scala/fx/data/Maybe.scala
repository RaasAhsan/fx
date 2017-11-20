package fx.data

sealed trait Maybe[+A]

final case class Just[A](a: A) extends Maybe[A]

final case object None extends Maybe[Nothing]
