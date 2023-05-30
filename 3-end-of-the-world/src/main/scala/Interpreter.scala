import fplibrary._

object Interpreter {
  def main(args: Array[String]): Unit = {
    print(Console.RED)

    // It can interpret any A
    def interpret[A](description: Description[A]): A =
      description.apply()

    val description: Description[Unit] =
      Program.createDescription(args)

    print(Console.GREEN)
    interpret(description)

    print(Console.RESET)
  }
}