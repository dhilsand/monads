package fplibrary

trait Monad[C[_]] {
  def flatMap[A, B](ca: C[A])(acb: A => C[B]): C[B]
  // @inline def bind[A, B](ca: C[A])(acb: A => C[B]): C[B]
  // @inline def >>=[A, B](ca: C[A])(acb: A => C[B]): C[B]
}

// We're gonna use Monads as Type classes.
// The square bracket notation [_: Monad] denotes a context bound, which requires an implicit
// instance of type class Monad for the type constructor C[_].
// implicitly[Monad[C]] is used to summon the implicit instance of type class Monad for the
// type constructor C[_]. This method retrieves the implicitly available instance at compile-time.
object Monad {
  def apply[C[_]: Monad]: Monad[C] = implicitly[Monad[C]]
}