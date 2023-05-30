package fplibrary

// Description is a tiny abstraction that can describe any
// kinds of effects - intputs / output or exceptions. It's not
// going to handle the exception in any way.
object Description {
  // By name parameter.
  // In Scala, parameters to functions are by default evaluated eagerly.
  // That means that the arguments are evaluated before they are passed
  // into the function. However, Scala also supports "by-name" parameters,
  // which are evaluated lazily.
  // A by-name parameter is defined with the syntax => Type (instead of Type)
  // in the function parameter list. This means that the argument is not
  // evaluated before the function is called, but rather each time the
  // argument is accessed within the function.

  def create[A](a: => A): Description[A] = () => a
}