package fx.data

class Kleisli[F[_], A, B](run: A => F[B]) {

}
