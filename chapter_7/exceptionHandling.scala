import java.io.FileReader
import java.io.FileNotFoundException
import java.io.IOException
import java.net.URL
import java.net.MalformedURLException

def half(n: Int) =
  if n % 2 == 0 then
    n / 2
  else
    throw RuntimeException("n must be even")

@main def m() =
  //throw new IllegalArgumentException // You throw exceptions like this

  try
    val f = new FileReader("input.txt")
  catch
    case ex: FileNotFoundException => println("File not found")
    case ex: IOException => println("IO error")
  

  val file = new FileReader("exceptionHandling.scala")
  try
    println("here " + file.read()) // This prints here 105 for some reason
  finally
    file.close()

  val path = "Some url"
  
  val urlFor =
    try new URL(path)
    catch case e: MalformedURLException =>
      new URL("http://www.scala-lang.org")

  
