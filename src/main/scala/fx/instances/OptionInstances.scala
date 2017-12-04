package fx.instances

import fx.Functor

trait OptionInstances {

  implicit def optionFunctor: Functor[Option] = new Functor[Option] {
    override def map[A, B](f: (A) => B)(a: Option[A]): Option[B] = {
      a.map(f)
    }
  }

}
