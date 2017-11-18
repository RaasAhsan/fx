package fx.algebra

trait Monoid[A] extends Semigroup[A] {
  def zero: A
  def add(a: A, b: A): A
}
