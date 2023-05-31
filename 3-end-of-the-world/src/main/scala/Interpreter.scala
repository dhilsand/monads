import fplibrary._

object Interpreter {
  def main(args: Array[String]): Unit = {
    print(Console.RED)

    // It can interpret any A
    def interpret[A](description: Description[A]): A =
      description.apply()

    val description: Description[Unit] =
      PointFreeProgram.createDescription(args)

    print(Console.GREEN)
    // All output from here should be GREEN.
    // This tells us that the entire program is running through the interpreter.
    // this means we're not doing any
    interpret(description)

    print(Console.RESET)
  }
}