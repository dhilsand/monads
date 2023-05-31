package fplibrary

object PointFree {
  /**
   *
   * Example `val ac = compose(ab, bc)`
   *
   * @param ab  function that takes a and produces b
   * @param bc  function that takes b and produces c
   * @return    function that takes a and produces c
   *
   */
  def compose[A, B, C](ab: A => B, bc: B => C): A => C = a => {
    // product a C over here.
    val b = ab(a)
    val c = bc(b)
    c
  }
}

