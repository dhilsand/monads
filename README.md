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
