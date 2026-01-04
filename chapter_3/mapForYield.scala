@main def m() =
  val adjectives = List("O", "T", "R", "B")

  val nouns = adjectives.map(adj => adj + "Fish")// Map works like map, returns a new object
  
  val nounsYield =
    for adj <- adjectives yield// Does exactly the same thing as map, speciall scala syntax
      adj + "Fish"

    val ques = Vector("Who", "What", "When")
    val usingMap = ques.map(q => q.toLowerCase + "?")
    val usingForYield =
      for q <- ques yield
        q.toLowerCase + "?"

    // Option[E]
    val startsW = ques.find(q => q.startsWith("W"))// Some(Who)
    val startsH = ques.find(q => q.startsWith("H"))// None
  
    startsW.map(word => word.toUpperCase)// Option supports map; Some(WHO)

    for word <- startsW yield word.toUpperCase// You can also write it with for yield

    startsH.map(word => word.toUpperCase)// None maps to None allways

    
