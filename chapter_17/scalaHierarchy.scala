@main def m() =
  // Any is the top of Scala hierarchy, all classes inherit from it
  // final def ==(that: Any): Boolean // same as equals
  // final def !=(that: Any): Boolean // negation of equals
  // def equals(that: Any): Boolean
  // def ##: Int
  // def hashCode: Int
  // def toString: String
  
  // AnyVal is a subclass of Any and a parent class of all values
  // Built in value classes in Scala are:
  // Byte, Short, Char, Int, Long, Float, Double, Boolean, and Unit
  // Implicit conversions are implemented between some classes (ex. Int/Long)

  // AnyRef represent all reference classes; It is == to Object from java
 
  // Comparison of default AnyVal objects will be done based
  // on the values stored in the obj, not the obj references
  // This can also be overriden in AnyRef by overriding equals method
  def isEqual(x: Int, y: Int) = x == y
  println(isEqual(421, 421)) // true
  def isEqualAny(x: Any, y: Any) = x == y
  println(isEqualAny(421, 421)) // true
  
  val x = "abcd".substring(2)
  val y = "abcd".substring(2)
  println(x == y) // true
  
  // eq method compares ref equality and cannot be overriden
  println(x eq y) // false

  // Null is a subclass of every AnyRef class
  // It is not compatible with value types!
  
  // val i: Int = null // Throws exception!

  // Nothing is a subclass of every other type
  // There exist no values of this type

  def error(message: String): Nothing = 
    throw new RuntimeException(message) // error in stdlib is defined something like this

  def divide(x: Int, y: Int): Int = 
    if y != 0 then x/y
    else sys.error("division by 0") // This works because Nothing is a subtype of everything 
                                    // including Int

  // There are union types (Int | String) and intersection types (&)
  // Those work similar as in Haskell
  // One example of usage:
  def errorMessage(msg: Int | String): String =
    msg match
      case n: Int => s"Error number: ${n.abs}"
      case s: String => s + "!"

// Custom value classes
// Only certain classes can be made into value classes. For a class to be
// a value class, it must have exactly one parameter and it must have nothing
// inside it except defs. Furthermore, no other class can extend a value class,
// and a value class cannot redefine equals or hashCode.
                      
class Dollars(val amount: Int) extends AnyVal:
  override def toString = "$" + amount
