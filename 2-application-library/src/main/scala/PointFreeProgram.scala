import fplibrary._

object PointFreeProgram {

  // We wrap the entire run into a description.                 = // function that takes no arguments
  def createDescription(args: Array[String]): Description[Unit] = () => {

    display(
      hyphens(
        display(
          createMessage(
            round(ensureAmountIsPositive(
              convertStringToInt(
                prompt(display(question(
                  display(hyphens())))))))))))
  }

  private def hyphens(input: Any): String = "\u2500" * 50

  private def question(input: Any): String =
    "How much money would you like to deposit?"

  // side effect (writing to the console)
  private def display(input: Any): Unit = {
    println(input)
  }

  // side effect (reading from the console)
  private def prompt(input: Any): String =
    scala.io.StdIn.readLine()

  // potential side effect (throwing of a NumberFormatException)
  private def convertStringToInt(input: String): Int =
    input.toInt

  private def ensureAmountIsPositive(amount: Int): Int =
    if (amount < 1)
      1
    else
      amount

  @scala.annotation.tailrec
  private def round(amount: Int): Int =
    if (isDivisibleByHundred(amount))
      amount
    else
      round(amount + 1)

  private def isDivisibleByHundred(amount: Int): Boolean =
    amount % 100 == 0

  private def createMessage(balance: Int): String =
    s"Congratulations, you now have USD $balance."
}
