package object fplibrary {
  // Type aliases cannot live outside an object. So we put them in package object.

  // A "thunk" in Scala is a term used to refer to a function that has zero arguments. 
  // Thunks are often used in lazy computations where evaluation is deferred until necessary. 
  // In a lazy evaluation context, thunks serve as placeholders for the actual values 
  // which are computed only when they are needed.

  // Here `Thunk` is a type that is a function that takes nothing and produces a result A.
  private type Thunk[A] = () => A

  // Just a type alias to represent the description of program
  type Description[A] = Thunk[A]

  // format: OFF
  private type RegularArrow[A, B      ] = A =>   B
  private type KleisliArrow[A, B, C[_]] = A => C[B]
  // format: ON

  // Monads are there for the composition of KleisliArrows

}