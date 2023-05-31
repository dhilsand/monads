import fplibrary._

object PointFreeProgram {

  // We wrap the entire run into a description.                 = // function that takes no arguments
  lazy val createDescription: Array[String] => Description[Unit] = args =>
    Description.create(
      display(
        hyphens(
          display(
            createMessage(
              round(
                ensureAmountIsPositive(
                  convertStringToInt(
                    prompt(
                      display(
                        question(
                          display(
                            hyphens()))))))))))))

  private lazy val hyphens: Any => String = _ =>
    "\u2500" * 50

  private lazy val question: Any => String = _ =>
    "How much money would you like to deposit?"

  // side effect (writing to the console)
  private lazy val display: Any => Unit = input =>
    println(input)

  // side effect (reading from the console)
  private lazy val prompt: Any => String = _ =>
    "5" //scala.io.StdIn.readLine()

  // potential side effect (throwing of a NumberFormatException)
  private lazy val convertStringToInt: String => Int = input =>
    input.toInt

  private lazy val ensureAmountIsPositive: Int => Int = amount =>
    if (amount < 1)
      1
    else
      amount

  private lazy val round: Int => Int = amount =>
    if (isDivisibleByHundred(amount))
      amount
    else
      round(amount + 1)

  private lazy val isDivisibleByHundred: Int => Boolean = amount =>
    amount % 100 == 0

  private lazy val createMessage: Int => String = balance =>
    s"Congratulations, you now have USD $balance."
}
