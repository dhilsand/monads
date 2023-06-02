package fplibrary

object PointFree {
  /**
   * Composes simple functions together. Simple functions = those functions that return values.
   * Example `val ac = compose(ab, bc)`
   *
   * @param ab  function that takes a and produces b
   * @param bc  function that takes b and produces c
   * @return    function that takes a and produces c
   *
   */
  def compose[A, B, C](ab: A => B, bc: B => C): A => C = a => {
    // I've been passed two functions, I just call those functions here.
    // I do the work of threading through the inputs/outputs i.e. the output of
    // one becomes the input to the next.
    // If any function fails, the whole chain of composed functions fail.
    val b = ab(a)
    val c = bc(b)
    c
  }

  /**
   * This takes higher-order functions as input instead of simple functions
   * i.e. - it deals with functions that return functions.
   *
   * @param adb a function that will return a function of type Description[B] i.e. `() => B`
   * @param bdc
   * @return
   */
  // def composeKleisli[A, B, C](adb: A => Description[B], bdc: B => Description[C]): A => Description[C] = a => {
  //   val db = adb(a)
  //   val b = db.apply()
  //   val dc = bdc(b)
  //   dc
  // }

  def composeKleisli[A, B, C, D[_]: Monad](adb: A => D[B], bdc: B => D[C]): A => D[C] = a => {
    val db = adb(a)

    // val b = db.apply()   // it fails, as we don't know the type of db function.
    // val dc = bdc(b)
    // dc

    val dc = Monad[D].flatMap(db)(bdc)
    dc
  }

  private def helper[A, B, C[_]](cb: C[A], acb: A => C[B]) = ???

  trait HasHelper[C[_]] {
    def helper[A, B](cb: C[A])(acb: A => C[B]): C[B]
  }

}

