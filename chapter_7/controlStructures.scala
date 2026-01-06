import scala.io.StdIn.readLine
import java.util.Locale.FilteringMode

@main def main(args: String*) =
  // If

  //var filename = "default.txt"
  //if !args.isEmpty then
  //  filename = args(0)
  
  val filename =
    if !args.isEmpty then args(0)
    else "default.txt"
  // value of the whole if statement is the value of the last statement in the chosen branch

  //Very cursed do while (i guess like in c, you can put multiple statements in while condtion
  //the last one is the check used)
  while
    val line = readLine()
    println(s"Read: $line")
    line != ""
  do ()
  var x = 0
  if (x = 3) == () then println("return value of an asignment is Unit")

  // For

  val filesHere1 = (new java.io.File(".")).listFiles
  for file <- filesHere1 do
    println(file)

  for i <- 1 to 4 do // 1.to(4) creates  range 1, 2, 3 , 4
    println(s"Iteration $i")

  for i <- 1 until 4 do // Doesnt include 4
    println(s"Iteration $i")

  // Filtering

  val filesHere = (new java.io.File(".")).listFiles

  for file <- filesHere if file.getName.endsWith(".scala") do
    println(file)

  for // Multiple filters are also allowed with the following syntax; This creates nested loops
    file <- filesHere
    if file.isFile
    if file.getName.endsWith(".scala")
  do println(file)

  val pattern = ".*gcd.*"
  for 
    file <- filesHere
    if file.getName.endsWith(".scala")
    line <- fileLines(file) // Since this is translated to nested loops, you can use intermediate
                            // results here
    if line.trim.matches(pattern)
  do println(s"$file: ${line.trim}")

  for 
    file <- filesHere
    if file.getName.endsWith(".scala")
    line <- fileLines(file)
    trimmed = line.trim // You can assign to variables here like so; they are val but you can 
                        // ommit that
    if trimmed.matches(pattern)
  do println(s"$file: $trimmed")
  
  // for yield returns a collection; It is a more functional approach
  val scalaFiles =
    for 
      file <- filesHere
      if file.getName.endsWith(".scala")
    yield file

      

def fileLines(file: java.io.File) =
  scala.io.Source.fromFile(file).getLines().toArray

// While

def gcdLoop(x: Long, y: Long): Long =
  var a = x
  var b = y
  while a != 0 do// While returns a Unit
    val temp = a
    a = b % a
    b = temp
  b

def gcdFunctional(x: Long, y: Long): Long =
  if y == 0 then x else gcdFunctional(y, x % y)
