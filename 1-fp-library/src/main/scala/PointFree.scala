package fplibrary

object PointFree {

  
  /**
    * 
    * Example `val ac = compose(ab, bc)`
    * 
    * @param ab  function that takes a and produces b
    * @param bc  function that takes b and produces c
    * @return    functhat that takes a and produces c
    * 
    */
  def compose[A, B, C](ab: A => B, bc: B => C): A => C = a => {
    // product a C over here.
    val b = ab(a)
    val c = bc(b)
    c
  }

  // def composeKleisli2[A, B, C, D[_]](adb: A => D[B], bdc: B => D[C]): A => D[C] = a => {
  //   val db = adb(a)

  //   val dc = helper(db, bdc)

  //   dc
  // }
}

