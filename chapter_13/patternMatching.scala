trait Expr 
case class Var(name: String) extends Expr 
case class Num(number: Double) extends Expr 
case class UnOp(operator: String, arg: Expr) extends Expr 
case class BinOp(operator: String, left: Expr, right: Expr) extends Expr

// Case classes also support pattern matching by creating an extractor method, unapply
def simplifyTop(expr: Expr): Expr =
  expr match
    case UnOp("-", UnOp("-", e)) => e
    case BinOp("+", e, Num(0)) => e
    case BinOp("*", e, Num(1)) => e
    case _ => expr
  // NOTE: After finding a match, rest of the cases are not checked nor executed. 
  // If there is no match, MatchError is thrown (you have to include default case)
  // Also match expresion has a value (the return of the matched case)
  // All in all it works the same as Rust match from what i can tell

def describe(x: Any) = // Any signifies any type
  x match
    case 5 => "five"
    case true => "true"
    case "hello" => "hi!"
    case Nil => "empty list"
    case _ => "something else"

@main def m() = 
  val v = Var("x") // We don't have to use new because we can use a factory method (Var.apply())
                   // which was auto created by the compiler because we declared a case class
  val op = BinOp("+", Num(1), v)

  // Case class creates val fields for each argument
  v.name
  op.left

  // Case classes also get implementations for toString, hashCode and equals
  println(op.toString) // BinOp(+,Num(1.0),Var(x))
  op.right == Var("x") //true

  op.copy(operator = "-") // Case classes also have a copy method; it uses named parameters and
                          // default values that are = to old ones

  val pi = 3.14
  val Pi = 3.14
  println(
  2 match 
    case Pi => s"Pi is taken as a constant here due to upper case, this will be skiped since 2 != $Pi"
    case `pi` => s"Using `pi` signals that pi should be looked at as a constant value, not a variable; if pi was part of an object this.pi or obj.pi would also have the same effect"
    case pi => s"pi is taken as a variable here due to lower case, this will be executed since 2 == $pi"
  )

  // Pattern matching with sequences and tuples
  val xs = List(1, 2, 3)

  println(
  xs match
    case List(1, _) => "List(1, something)"
    case List(1, _*) => "List(1, ...)"
    case _ => ""
  )

  def tupleDemo(obj: Any) =
    obj match
      case (a, b, c) => s"matched $a$b$c"
      case _ => ""

  println(tupleDemo("a ", 3, "-tuple"))

  // Typed patterns

  def generalSize(x: Any) = 
    x match 
      case s: String => s.length // Matches every NON-NULL instance of String
      case m: Map[_, _] => m.size
      case _ => -1

  println(generalSize("abc"))
  println(generalSize(Map(1 -> "a", 2 -> "b")))
  println(generalSize(math.Pi))

  // Manual type checking and casting in Scala
  // The operators isInstanceOf and asInstanceOf are treated as prede-
  // fined methods of class Any that take a type parameter in square brackets.
  val x: Any = "String"
  println(x.isInstanceOf[String]) // True
  println(x.asInstanceOf[String])

  // Generics don't know the type of their fields at runtime for some reason, this is called
  // type erasure. So the function like the one below will emit a warrning during compilation
  // and provide some wierd results at runtime (We still see it is Java underneath :D)

  def isIntMap(x: Any) = 
    x match
      case m: Map[Int, Int] => true
      case _ => false

  println(isIntMap(Map(1 -> 1))) // True
  println(isIntMap(Map("a" -> "b"))) // Also true !!!!
  println(isIntMap(3))

  // Exception to type erasure are Arrays since they are handled specially
  
  // Variable binding
  def removeDoubleAbs(expr: Expr): Expr =
    expr match
      case UnOp("abs", e @ UnOp("abs", _)) => e
      case _ => expr

  // Pattern guards
  
  def simplifyAdd(e: Expr) = 
    e match
      case BinOp("+", x, y) if x == y =>
        BinOp("*", x, Num(2))
      case _ => e
  // You can add sealed keyword to a class - this makes sure that no subclasses can be added 
  // except in this file. This then alows the compiler to assist better with pattern matching
  // allowing you to omit the default case if you covered all the subtypes
  // You can also use (e: @unchecked) match annotation - this tells the compiler that it should 
  // not complain if we are not covering all the possible cases

  // There is an Option type and it works the same as in Rust

  // You can also use case to define partial functions; whole thing is very odd and probably
  // should be avoided.
  val second: List[Int] => Int =
    case x :: y :: _ => y

  val secondPartial: PartialFunction[List[Int],Int] =
    case x :: y :: _ => y

  secondPartial.isDefinedAt(List(5, 6, 7)) // True
  secondPartial.isDefinedAt(List(7)) // False
