package fplibrary

object PointFree {
  // val ac = compose(ab, bc)
  def compose[A, B, C](ab: A => B, bc: B => C): A => C = a => {
    // product a C over here.
    val b = ab(a)
    val c = bc(b)
    c
  }
}

