@main def m(args: String*) = 
  val firstArg = if !args.isEmpty then args(0) else ""

  firstArg match // Match works like in rust, it returns a value
    case "salt" => println("peper")
    case "chips" => println("salsa")
    case "eggs" => println("bacon")
    case _ => println("huh?")
  
  // There is no break and continue in scala
