import scala.collection.mutable.ListBuffer
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable
import scala.collection.immutable.TreeSet
import scala.collection.immutable.TreeMap

@main def m() =
  // Array
  val fiveInts = new Array[Int](5) // Array(0, 0, 0, 0, 0)
  val arr = Array(5, 4, 3, 2, 1)
  fiveInts(0) = arr(4)
  println(fiveInts.toList)

  // ListBuffer
  // Constant append and prepend (+= and +=:)
  val buf = new ListBuffer[Int]
  buf += 1 // ListBuffer(1)
  buf += 2 // ListBuffer(1,2)
  3 +=: buf // ListBuffer(3,1,2)
  println(buf.toList)

  // ArrayBuffer
  // Expandable Array
  val bufA = new ArrayBuffer[Int]()

  bufA += 12
  bufA += 15
  println(bufA.toList) // List(12,15)

  // StringOps
  // Sequence of chars
  // Predef has an implicit conversion from String to StringOps
  def hasUpperCase(s: String) = s.exists(_.isUpper) // String doesn't have exists, but StringOps
                                                    // does, so we have an implicit conversion
  println(hasUpperCase("aAA")) // True
  
  // Sets and maps
  // Default variants are immutable, mutable must be imported
  val text = "Some sentence that has a lot of words, duplicate words, sentence some"
  val wordsArray = text.split("[ !,.]+") // \s+ doesn't work ?!
  val words = mutable.Set.empty[String]
  for word <- wordsArray do
    words += word.toLowerCase
  println(words)

  val map = mutable.Map.empty[String, Int]
  map("hello") = 1
  map("hello") // 1
  def countWords(text: String) =
    val counts = mutable.Map.empty[String, Int]
    for rawWord <- text.split("[ ,!.]+") do
      val word = rawWord.toLowerCase
      val oldCount =
        if counts.contains(word) then counts(word)
        else 0
      counts += (word -> (oldCount + 1))
    counts
  countWords("See Spot run! Run, Spot. Run!")
  // Map(spot -> 2, see -> 1, run -> 3)

  // Sorted set and map - TreeSet and TreeMap
  val ts = TreeSet(9, 3, 54, 1, 5, 76, 8)
  println(ts)

  val tm = TreeMap(3 -> 'x', 1 -> 'x', 2 -> 'x')
  println(tm)

  // If you declare immutable as val += on it will fail. But:
  var people = Set("Nancy", "Bob")
  people += "Jane" // This creates a new set implicitly
  println(people)

  val mutSet = ts to mutable.Set
  val immutSet = mutSet to Set

  // Tuples
  def longestWord(words: Array[String]): (String, Int) =
    var word = words(0)
    var idx = 0
    for i <- 1 until words.length do
      if words(i).length > word.length then
        word = words(i)
        idx = i
    (word, idx)

  val longest = longestWord("A sentence with couple words".split(" "))
  println(longest) // (sentence,1)

  longest(0) // sentence
  longest(1) // 1

  val (word, idx) = longest // Pattern matching

