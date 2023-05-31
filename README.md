# Monads

Someimes also called **containers**, **boxes**, **programmable semi-colons**.

Monads are a mechanism that make automatic composition of special kind of functions possible.
Functional programming wouldn't be possible without them.

A regular function (in mathemtical sense), takes input of type A and produces some output of type B.
In FP, a program that uses such a fn, can observe its output B and process it further.
In other words, B is the `effect` that the function has on the program which is recorded in the type signature of the function.
B is known as a `pure-effect` that a function produces. Any other effect apart from B (i.e. any **side-effect**) cannot be observed by the rest of the program.

Side effects include things like: assigning a new value (mutating a variable), printing to console, writing to DB etc.

Since a functional program cannot observe those side-effects, fns that produce those side effects are useless to functional programmers. So functional programmers don't write their programs using functions that produce side effects. A special kind of function is used instead that **attaches additional information to the output B that describes the side-effect**. The side-effect thus becomes a `pure-effect`.

As all effects are now recorded in the type system, and they become much easier to track and the entire program becomes much easier to reason about **independent of its size**. This is one of the many reasons why functional programmers keep insisting on programming only with pure effects instead of side-effects.

We need a common interface for composing functions, and the common interface is that on **Monads**. Everything that has `flatMap` is a **monad**.

---

Instead of admitting that it is impossible to write a useful program without side effects, we separate the parts that are **pure** from the parts that are **impure**. We minimize the parts that are impure. We can separate them into separate projects.

In our entire application, instead of performing side-effects, we switch to describing side-effects, and then by end we will have an interpreter that will interpret the description and it will be the only place that will perform side effects.

---

## Function Types

In Scala, functions are first-class citizens and have their own types. Each function based on its argument types and return type has a specific function type. This means that you can use functions as values, pass them as parameters, and return them from other functions. It's a core part of how Scala supports functional programming.

For example, a function that takes an `Int` and a `String` and returns a `Boolean` would have the type `(Int, String) => Boolean`.

Here's how you can use it.

```scala
def checkLength(n: Int, s: String): Boolean = s.length > n

val myFunction: (Int, String) => Boolean = checkLength
```

## By-name parameters (passing a computation instead of value)

By-name parameters in Scala are used to pass expressions. These expressions
could represent some computation.

By-name parameters work by taking an **expression as an argument** and evaluating it each time it is used within the function. In this sense, it's a "computation" rather than a "value".

```scala
def logDebug(message: => String) {
  if (debugEnabled) {
    println(message)
  }
}
```

In this function, `message` is a by-name parameter. If `debugEnabled` is `false`, then message will never be evaluated at all, which could save time if message is a complex string to compute. On the other hand, if `debugEnabled` is `true`, then `message` will be evaluated once for each usage within the function.

This technique allows for significant performance improvements when the passed expression is computationally expensive and might not always be needed.

## Scala is expression-oriented language

In an expression-oriented language, almost everything (if not everything) is an expression and yields a value. Scala, being an expression-oriented language, adheres to this principle: most constructs and code blocks that would be considered statements (and thus wouldn't return a value) in other languages are considered expressions in Scala and thus yield a value.

For instance, in many languages, if-else structures are statements and don't return a value. However, in Scala, if-else structures are expressions and do return a value. Here's an example:

```scala
val max = if (a > b) a else b
```

In this example, the if-else expression is used to compute a value, which is then assigned to the variable max.

## Code blocks {} are expressions

Similarly, even blocks of code delimited by `{}` are expressions in Scala. They can contain several expressions, and the value of the last expression in the block is the value of the whole block. For example:

```scala
val result = {
  val x = computeSomething()
  val y = computeSomethingElse()
  x + y
}
```

In this example, the block is an expression that computes `x + y`, and this value is assigned to `result`.

In contrast, in statement-oriented languages (like earlier versions of Java), constructs like if-else and code blocks do not yield values and are used primarily for their side effects (like modifying variables or performing I/O).

Being expression-oriented is a characteristic of functional programming languages, and it often leads to more concise and readable code, since you can use complex expressions as part of larger expressions, rather than having to break down computations into a sequence of statements.

## Thunk

In Scala, a thunk is essentially a **function with no arguments** and some specific computational logic, usually **used to delay computation**. It can be thought of as a chunk of computation that isn't executed right away but is instead **stored for later use**. A common use case for thunks in Scala is for lazy evaluation, where you postpone computation until it's actually necessary.

```scala
// type alias for a function with no parameters that returns A. 
// A is type parameter.
private type Thunk[A] = () => A
```


```scala
// Just a type alias to represent the description of program which is basically a thunk.

type Description[A] = Thunk[A]
```

## Point-free or Tacit Programming Style

Point-free programming, also known as tacit programming, is a programming paradigm in which function definitions do not explicitly mention their arguments. Instead of manipulating data directly, you compose and apply functions.

The name "point-free" comes from the idea that this style avoids the need to mention "points" (the values or variables being operated on). The benefits can include more concise, readable code and the potential for greater reusability of functions.

Here's an example in Scala.

Let's say you have a function that takes a list of integers, increments each one, and then sums them:

```scala
def incrementAndSum(list: List[Int]): Int = list.map(_ + 1).sum
```
This function isn't point-free because it explicitly mentions its argument list.

### Point-free version

```scala
val incrementAndSum: List[Int] => Int = _.map(_ + 1).sum
```

Point-free programming can lead to very concise, elegant code, but it can also become difficult to understand if overused. It's a tool that's useful to have in your toolbox, but like all tools, it's important to use it appropriately.



