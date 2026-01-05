import scala.collection.mutable

class ChecksumAccumulator:
  private var sum = 0

  def add(b: Byte): Unit = sum += b
  
  def checksum(): Int = ~(sum & 0xFF) + 1

object ChecksumAccumulator:// This is scala way of handling static fields - this is a singleton
                           // object related to (companion object) ChecksumAccumulator class
  private val cache = mutable.Map.empty[String, Int]

  def calculate(s: String): Int =
    if cache.contains(s) then
      cache(s)
    else
      val acc = new ChecksumAccumulator
      for c <- s do
        acc.add((c >> 8).toByte)
        acc.add(c.toByte)
      val cs = acc.checksum()
      cache += (s -> cs)
      cs

case class Person(name: String, age: Int): // The case keyword tells the compiler to make: 
                                           // companion object with a factory method apply (so
                                           // you can create an object with val p = 
                                           // Person(a, 2)), fields with the same name as those
                                           // specified and accessor methods, toString, toHash
                                           // Compiler will not make anything we specify 
                                           // ourselves. It will also make a copy and unapply 
                                           // methods that will be covered later
  def appendToName(suffix: String): Person =
    Person(s"$name$suffix", age)

object Person:
  def apply(name: String, age: Int): Person =
    val capitalizedName =
      if !name.isEmpty then 
        val firstChar = name.charAt(0).toUpper
        val restOfName = name.substring(1)
        s"$firstChar$restOfName"
      else throw new IllegalArgumentException("Empty name")
    new Person(capitalizedName, age)

//@main def m() =
//  new ChecksumAccumulator
//  println(ChecksumAccumulator.calculate("Hello"))
//  println(("s", 24).toString)
