package fx.algebra

trait Semigroup[A] {
  def add(a: A, b: A): A
}
