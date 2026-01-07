// Methods
object Padding:
  def padLines(text: String, minWidth: Int): String =
    val paddedLines = 
      for line <- text.linesIterator yield
        padLine(line, minWidth)
    paddedLines.mkString("\n")

  private def padLine(line: String, minWidth: Int): String =
    if line.length >= minWidth then line
    else line + " " * (minWidth - line.length)

// Local functions

def padLines(text: String, minWidth: Int): String =
  def padLine(line: String): String = // Local function can access parameters of the enclosing
                                      // function
    if line.length >= minWidth then line
    else line + " " * (minWidth - line.length)
  
  val paddedLines = 
    for line <- text.linesIterator yield
      padLine(line)
  paddedLines.mkString("\n")

@main def m() = 
  // First-class functions

  val increase = (x: Int) => x + 1
  increase(10)

  val addTwo = (x: Int) =>
    val increment = 2
    x + increment
  addTwo(10)

  val someNumbers = List(-11,-10,-5,0,5,10)
  someNumbers.foreach((x: Int) => println(x))

  someNumbers.filter((x: Int) => x > 0)
  someNumbers.filter(x => x > 0) // Type can be auto infered
  someNumbers.filter(_ > 0) // This is also legal, you can use it if every parameter appears
                                 // only once
  val f = (_: Int) + (_: Int) // Here we need to specify the type since there is no other way
                              // for compiler to deduce it
                              
  // Currying
  
  def sum(a: Int, b: Int, c: Int): Int =
    a + b + c

  // If you want to pass the function that was declared normaly (def or method) you need to curry
  // on all arguments like:

  val a = sum(_, _, _) // This can be also written like val a = sum
  a(1, 2, 3)

  // You can also do partial application
  val b = sum(3, _, 11)

  b(3)

  // Closures
  
  var more = 1
  val addMore = (x: Int) => println(x + more)
  addMore(10)

  more = 9
  addMore(10) // this prints 19; function captures the reference to a variable, not its value

  var arraySum = 0
  someNumbers.foreach(arraySum += _)
  println(arraySum) // sum is changed and = to the sum of the array

  // if a closure captures a variable from a function for example, the instance that is tied
  // to a specific run when the closure was created is used
  def makeIncreaser(more: Int) = (x: Int) => println(x + more)

  val inc1 = makeIncreaser(3)
  val inc2 = makeIncreaser(200)

  inc1(2)
  inc2(2)

  // Special function call forms

  def echo(args: String*) = // echo takes zero to many String args
    for arg <- args do println(arg) // args: Seq[String] here

  echo()
  echo("One")
  echo("One", "two")
  
  val seq = Seq("a", "b", "c")
  echo(seq*) // echo(seq) wouldn't work even though args get converted to Seq[String]
             // echo(seq*) takes all the arguments in seq and passes them to function
             // so this is == to echo("a", "b", "c")

  def speed(distance: Float, time: Float) = distance / time 
  speed(time = 10, distance = 100) // You can call arguments by name

  def addNumber(a: Int, b: Int = 1): Int = a + b // b has a default value of 1 here

  addNumber(4)

  // Tail recursions will be auto replaced with more eficient loop versions by compiler
