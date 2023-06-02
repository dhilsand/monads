package fplibrary

// Description is a tiny abstraction that can describe any
// kinds of effects - intputs / output or exceptions. It's not
// going to handle the exception in any way.
object Description {
  // By name parameter.

  // By-name parameters in Scala are used to pass expressions. These expressions
  // could represent some computation.

  // In Scala, parameters to functions are by default evaluated eagerly.
  // That means that the arguments are evaluated before they are passed
  // into the function. However, Scala also supports "by-name" parameters,
  // which are evaluated lazily.
  // A by-name parameter is defined with the syntax => Type (instead of Type)
  // in the function parameter list. This means that the argument is not
  // evaluated before the function is called, but rather each time the
  // argument is accessed within the function.

  def create[A](a: => A): Description[A] = () => a
  // returns a function that takes no parameters, hence () and when
  // called, it will evaluate and return the value of `a`.

  // equivalent
  // def create[A](a: => A): () => A = () => a

  implicit val M: Monad[Description] = new Monad[Description] {
    final override def flatMap[A, B](ca: Description[A])(acb: A => Description[B]): Description[B] = Description.create {
      val a = ca.apply() // executes the first function (i.e. the function on which flatMap is called)
      val cb = acb(a)    // uses the second function to produce a function
      val b = cb.apply() // executes the produced function
      b
    }
  }
}