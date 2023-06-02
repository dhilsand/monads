package object fplibrary {
  // Type aliases cannot live outside an object. So we put them in package object.

  // A "thunk" in Scala is a term used to refer to a function that has zero arguments.
  // Thunks are often used in lazy computations where evaluation is deferred until necessary.
  // In a lazy evaluation context, thunks serve as placeholders for the actual values
  // which are computed only when they are needed.

  // Here `Thunk` is a type that is a function that takes nothing and produces a result A.
  // It's giving a new name, Thunk[A], to the type of a parameterless function that returns a value of type A
  private type Thunk[A] = () => A

  // Just a type alias to represent the description of program which is basically a thunk.
  type Description[A] = Thunk[A]

  // format: OFF
  private type RegularArrow[A, B      ] = A =>   B
  private type KleisliArrow[A, B, C[_]] = A => C[B]
  // format: ON
  // Monads are there for the composition of KleisliArrows

  // Scala, as a hybrid object-oriented and functional programming language,
  // has a special feature called "eta expansion" that can turn methods
  // (defined with def) into function values. This allows for a seamless
  // integration between object-oriented style (methods) and functional
  // programming style (higher-order functions).

  // Type class, creates extension methods.
  implicit final class InfixNotationForPointFree[A, B](private val ab: A => B) extends AnyVal {
    @inline def `;`[C](bc: B => C): A => C = PointFree.compose(ab, bc)
    @inline def `.`[C](bc: B => C): A => C = PointFree.compose(ab, bc)
    @inline def `-->`[C](bc: B => C): A => C = PointFree.compose(ab, bc)
  }

  implicit final class InfixNotationForPointFreeKleisli[A, B, D[_]](private val adb: A => D[B]) extends AnyVal {
    @inline def `;;`[C](bdc: B => D[C])(implicit M: Monad[D]): A => D[C] = PointFree.composeKleisli(adb, bdc)
    @inline def `>=>`[C](bdc: B => D[C])(implicit M: Monad[D]): A => D[C] = PointFree.composeKleisli(adb, bdc)
  }

}