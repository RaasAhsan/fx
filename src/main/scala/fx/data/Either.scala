package fx.data

sealed trait \/[A, B] { self =>

  // Right-biased map
  def map[C](f: B => C): \/[A, C] = self match {
    case \/-(value) => \/-(f(value))
    case -\/(value) => -\/(value)
  }

  def leftMap[C](f: A => C): \/[C, B] = self match {
    case \/-(value) => \/-(value)
    case -\/(value) => -\/(f(value))
  }

}

final case class \/-[A, B](value: B) extends \/[A, B]

final case class -\/[A, B](value: A) extends \/[A, B]
