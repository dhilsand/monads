version := "0.0.1-SNAPSHOT"

organization := "somesome"

scalaVersion := "2.12.17"

// If we're using continous complication or continous running of test, 
// this will make sure that everytime we re-run the output is cleared.
triggeredMessage := Watched.clearWhenTriggered

autoStartServer := false

scalacOptions ++= Seq(
  "-feature",
  "-deprecation",
  "-language:implicitConversions",
  "-language:higherKinds",
  "-Ypartial-unification"
)

ThisBuild / shellPrompt := (_ => fancyPrompt(name.value))

def fancyPrompt(projectName: String): String = 
  s"""|
      |[info] Welcome to the ${cyan(projectName)} project!
      |sbt> """.stripMargin

def cyan(projectName: String): String = 
  scala.Console.CYAN + projectName + scala.Console.RESET

addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.9")

addCommandAlias("ll", "projects")
addCommandAlias("cd", "project")
addCommandAlias("root", "project monads")
addCommandAlias("lib", "project fp-library")
addCommandAlias("app", "project application-library")
addCommandAlias("main", "project end-of-the-world")
