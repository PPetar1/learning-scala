import scala.collection.mutable

@main def m() =
  var jetSet = Set("B", "A")// Creates an immutable Set
  jetSet += "L"// Creates and returns a new Set, we reasign to jetSet here, thats why it is var
  val query = jetSet.contains("C")
  
  val movieSet = mutable.Set("s", "m")// Creates a mutable Set
  movieSet += "p"// Modifies the set to include "p"

  val treasureMap = mutable.Map.empty[Int, String]
  treasureMap += (1 -> "G")// 1 -> "G" invokes 1.->("G") which returns a tuple (1, "G")
  treasureMap += (2 -> "F")
  treasureMap += (3 -> "D")
  val step2 = treasureMap(2)

  val romanNumeral = Map(// This is the default, immutable map
    1 -> "I", 2 -> "II"
  )
  val two = romanNumeral(2)
