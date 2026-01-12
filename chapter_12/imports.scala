import bobsdelights.Fruits
import bobsdelights.Fruit
//import Fruits.{Apple as Jabuka, Orange}// Renaming and hiding
import java.sql as S // Renaming
//import Fruits.{Apple as _, *} // Hiding only Apple

// using import on objects
def showFruit(fruit: Fruit) =
  import fruit.*
  s"${name}s are $color"

// Scala’s flexible imports
// Scala’s import clauses are quite a bit more flexible than Java’s. There
// are three principal differences. In Scala, imports:
// • may appear anywhere
// • may refer to objects (singleton or regular) in addition to packages
// • let you rename and hide some of the imported members

// These are imported by default
import java.lang.* // everything in the java.lang package
import scala.*// everything in the scala package
import Predef.*// everything in the Predef object

// Visibility and companion objects
class Rocket:
  import Rocket.fuel
  private def canGoHomeAgain = fuel > 20

object Rocket:
  private def fuel = 10
  def chooseStrategy(rocket: Rocket) =
    if rocket.canGoHomeAgain then
      goHome()
    else
      pickAStar()
  def goHome() = {}
  def pickAStar() = {}

@main def m() =
  println(Fruits.Apple)
