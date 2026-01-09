import Element.elem

abstract class Element:
  def contents: Vector[String] // This is an abstract method
  // These are parameterless methods and they can be written without (). The goal is to make 
  // client code the same whether we define them as fields or methods, and it is used like that
  // when the method just reads the class state and doesn't change it
  def height: Int = contents.length // Field would be val height = contents.length
                                    // and it would be initialized with the class itself
  def width: Int = if height == 0 then 0 else contents(0).length
  
  infix def above(that: Element): Element = 
    val this1 = this.widen(that.width)
    val that1 = that.widen(this.width)
    elem(this1.contents ++ that1.contents)

  infix def beside(that: Element): Element =
    // val newContents = new Array[String](this.contents.length)
    // for i <- 0 until this.contents.length do
    //   newContents(i) = this.contents(i) + that.contents(i)
    // VectorElement(newContents.toVector)
    // ^^Imperative style^^
    val this1 = this.heighten(that.height)
    val that1 = that.heighten(this.height)
    
    elem(
      for (line1, line2) <- this1.contents.zip(that1.contents)
      yield line1 + line2
    )

  def widen(w: Int): Element = 
    if w <= width then this
    else
      val left = elem(' ', (w - width) / 2, height)
      val right = elem(' ', w - width - left.width, height)
      left beside this beside right

  def heighten(h: Int): Element =
    if h <= height then this
    else
      val top = elem(' ', width, (h - height) / 2)
      val bot = elem(' ', width, h - height - top.height)
      top above this above bot

  override def toString = contents.mkString("\n")

end Element


object Element: // Factory object, responsible for creation of all classes/subclasses
  // Subclasses
  private class VectorElement(conts: Vector[String]) extends Element:
    val contents: Vector[String] = conts // Fields and methods share a namespace; You can 
                                         // overwrite contents method with a field like so
                                         // You could also just write 
                                         // class VectorElement(val contents: Vector[String])
                                         // extends Element
                                         // That is called a parametric field
  
  // Generally, Scala has just two namespaces for definitions in place of Java’s
  // four. Java’s four namespaces are fields, methods, types, and packages. By
  // contrast, Scala’s two namespaces are:
  // • values (fields, methods, packages, and singleton objects)
  // • types (class and trait names)
  
  private class LineElement(s: String) extends VectorElement(Vector(s)): // Here we invoke the 
                                                                         //superclass constructor
    override def width = s.length
    override def height = 1
  
  private class UniformElement(
    ch: Char,
    override val width: Int,
    override val height: Int
  ) extends Element:
    private val line = ch.toString * width 
    def contents = Vector.fill(height)(line)
  
  def elem(contents: Vector[String]): Element =
    VectorElement(contents)

  def elem(chr: Char, width: Int, height: Int): Element =
    UniformElement(chr, width, height)

  def elem(line: String): Element =
    LineElement(line) 

end Element
  
// final modifier makes sure it is impossible to override a member, an error will be the
// Adding final to a whole class makes it impossible to define a subclass of it

// @main def m() =
//   val ve = elem(Vector("hello", "world"))
//   println(s"${ve.height} ${ve.width}")
//   val e: Element = elem(Vector("hello")) // Subclasses work as one would expect

object Spiral:
  val space = elem(" ")
  val corner = elem("+")
  
  def spiral(nEdges: Int, direction: Int): Element =
    if nEdges == 1 then
      elem("+")
    else
      val sp = spiral(nEdges - 1, (direction + 3) % 4)
      def verticalBar = elem('|', 1, sp.height)
      def horizontalBar = elem('-', sp.width, 1)
      if direction == 0 then
        (corner beside horizontalBar) above (sp beside space)
      else if direction == 1 then
        (sp above space) beside (corner above verticalBar)
      else if direction == 2 then 
        (space beside sp) above (horizontalBar beside corner)
      else
        (verticalBar above corner) beside (space above sp)
  def main(args: Array[String]) = 
    val nSides = args(0).toInt
    println(spiral(nSides, 0))

end Spiral
