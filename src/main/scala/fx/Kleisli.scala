package fx

class Kleisli[F[_], A, B](run: A => F[B]) {

}
