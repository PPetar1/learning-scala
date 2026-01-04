def printArgs(args: List[String]): Unit =
  var i = 0// Using vars is viewed as a sign of imperative style
  while i < args.length do
    println(args(i))
    i += 1

def printArgsFunctional(args: List[String]): Unit =// Unit (void) is another sign of imperative
  for arg <- args do// Or args.foreach(println)
    println(arg)

def formatArgsNoSideEffects(args: List[String]) = args.mkString("\n")// Returns a String made 
                                                                     // of args and \n between


@main def m() =
  val args = List("a", "b", "c")
  printArgs(args)
  printArgsFunctional(args)
  println(formatArgsNoSideEffects(args))
