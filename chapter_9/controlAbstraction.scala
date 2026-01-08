import java.io.PrintWriter
object FileMatcher: // Remainder - this is a singleton
  private def filesHere = (new java.io.File(".")).listFiles

  def filesEnding(query: String) = 
    for file <- filesHere if file.getName.endsWith(query)
    yield file

  def filesContaining(query: String) = 
    for file <- filesHere if file.getName.contains(query)
    yield file

  // Previous two functions can be generalized by using function values like so:
  def filesMatching(query: String, matcher: (String, String) => Boolean) = 
    for file <- filesHere if matcher(file.getName, query)
    yield file

  // We can also create specific functions from the general like so:
  def filesRegex(query: String) = 
    filesMatching(query, _.matches(_)) // _.matches(_) = (x: String, y: String) => x.matches(y)

  // You can also move query to the function you pass, so you 
  // def filesMatching(matcher: (String) => Boolean) and then pass _.matches(query)
  // Because you would pass query here as a free argument (captured from the env), 
  // _.mathes(query) would be a closure!


def containsNeg(nums: List[Int]): Boolean = 
  var exists = false
  for num <- nums do
    if num < 0 then 
      exists = true
  exists

def betterWay(nums: List[Int]) = nums.exists(_ < 0)

// Currying

def curriedSum(x: Int)(y: Int) = x + y
def first(x: Int) = (y: Int) => x + y // Same as above

// Using functional programming like this creates our custom controll structures:
def withPrintWriter1(file: java.io.File, op: java.io.PrintWriter => Unit) =
  val writer = new PrintWriter(file)
  try op(writer)
  finally writer.close()

def withPrintWriter(file: java.io.File)(op: java.io.PrintWriter => Unit) =
  val writer = new PrintWriter(file)
  try op(writer)
  finally writer.close()

var assertionsEnabled = false
def myAssert(predicate: => Boolean) = // This can be written instead of () => Boolean
                                      // This works only for parameters!
  if assertionsEnabled && !predicate then
    throw new AssertionError

@main def m() =
  println(curriedSum(1)(2))
  val second = first(1)
  println(second(2))
  println(first(1)(2))

  withPrintWriter1(
    new java.io.File("date.txt"),
    writer => writer.println(new java.util.Date)
  )

  // You can put {} instead of () for functions that take one arg. If you combine this with 
  // currying you can create stuff that looks like hard coded language syntax even though they
  // are just fns:

  val file = new java.io.File("date.txt")
  withPrintWriter(file) { writer =>
    writer.println(new java.util.Date)
  }

  myAssert(3/0 == 0) // You can write this like so, instead of myAssert(() => x/0 == 0) because
                     // myAssert uses a by-name parameter.
                     // x/0 will not throw an error since you are delaying the evaluation and 
                     // assertionsEnabled == false
