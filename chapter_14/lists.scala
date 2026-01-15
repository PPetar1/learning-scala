@main def m() =
  // Lists are immutable arrays that are implemented as a single linked list
  val diag3 = 
    List(
      List(1, 0, 0),
      List(0, 1, 0),
      List(0, 0, 1)
    )
  val empty: List[Nothing] = List()
  // The list type in Scala is covariant. This means that for each pair of
  // types S and T, if S is a subtype of T, then List[S] is a subtype of List[T].
  // For instance, List[String] is a subtype of List[Object]. This is natural
  // because every list of strings can also be seen as a list of objects.1
  // Note that the empty list has type List[Nothing]. Nothing is consid-
  // ered the “bottom type” of Scala’s class hierarchy. It is a special type that is
  // a subtype of every other Scala type. Because lists are covariant, it follows
  // that List[Nothing] is a subtype of List[T] for any type T. So the empty
  // list object, which has type List[Nothing], can also be seen as an object
  // of every other list type of the form List[T]. That’s why it is permissible to
  // write code like:
  // // List[Nothing] is also of type List[String]!
  // val xs: List[String] = List()

  // :: (cons) expresses list extension at the front; Since it ends in a : it is right associative
  val diag3_ = (1 :: 0 :: 0 :: Nil) :: (0 :: 1 :: 0 :: Nil) :: (0 :: 0 :: 1 :: Nil) :: Nil

  // Basic operations
  println(diag3.head)
  println(diag3.tail)
  println(diag3.isEmpty)

  // List patterns
  val List(a, b, c) = diag3
  val q :: w :: rest = diag3
  diag3 match
    case List() => println("empty list")
    case x::rest => println(s"List that has first element = $x")

  // First-order methods on Lists (methods that don't take functions as arguments)
  val list = List(1, 2) ::: List(3, 4, 5) // List(1,2,3,4,5)
  println(List(1, 2, 3).length) // 3
  println(list.last) // 5
  println(list.init) // List(1,2,3,4)
  println(list.reverse) // List(5,4,3,2,1)
  println(list.take(3)) // List(1,2,3)
  println(list.drop(2)) // List(3,4,5)
  println(list.splitAt(2)) // (List(1,2), List(3,4,5))
  println(list(2)) // Same as list.apply(2), semantically meaning list[2]; returns 3
                   // O(n) because List is implemented as linked list
  println(diag3.indices) // Range 0 until 3
  println(diag3.flatten) // List(1,0,0,0,1,0,0,0,1)
  println(diag3.indices.zip(diag3)) // Vector((0,List(1,0,0), (1, List(0,1,0)), (2, List(0,0,1))))
  // if you zip two lists of different length, unmatched elements are dropped
  println(diag3.zipWithIndex) // List((List(1, 0, 0),0), (List(0, 1, 0),1), (List(0, 0, 1),2))
  // Any list of tuples can be changed back to a tuple of lists using unzip
  val zipped = diag3.indices.zip(diag3)
  println(zipped.unzip) // (Vector(0, 1, 2),Vector(List(1, 0, 0), List(0, 1, 0), List(0, 0, 1)))
  println(list.mkString("[", ",", "]")) // [1,2,3,4,5]
  val arr = list.toArray
  arr.toList
  val it = list.iterator
  it.next()
  it.next()

  // Higher order methods on Lists
  println(list.map(_ + 1)) // List(2,3,4,5,6)
  println(List.range(1, 5).flatMap( // flatMap also flattens the result
    i => List.range(1, i).map(j => (i, j))
  ))
  var sum = 0
  list.foreach(sum += _) // foreach argument do something
  println(sum) // 15

  println(list.filter(_ % 2 == 0)) // List(2, 4)
  println(list.partition(_ % 2 == 0)) // (List(2,4), List(1,3,5))
  println(list.find(_ % 2 == 0)) // Some(2)
  println(list.find(_ <= 0)) // None
  
  println(List(1,2,3,-4,5).takeWhile(_ > 0)) // List(1,2,3)
  println(List(1,2,3,-4,5).dropWhile(_ > 0)) // List(-4,5)
  println(List(1,2,3,-4,5).span(_ > 0)) // (List(1,2,3), List(-4,5))
                                        // xs span p === (xs takeWhile p, xs dropWhile p)

  println(diag3.exists(row => row.forall(_ == 0))) // false
                                                   // xs forall p === !(xs exists !p)

  def mySum(xs: List[Int]): Int = xs.foldLeft(0)(_ + _)
  println(mySum(list))
  def flattenRight[T](xss: List[List[T]]) =
    xss.foldRight(List[T]())(_ ::: _)
  println(list.sortWith(_ > _)) // List(5,4,3,2,1)

  // Methods of the List companion object
  List(1,2,3) // List.apply(1,2,3)
  List.range(1,5) // List(1,2,3,4)
  List.range(1,9,2) // List(1,3,5,7)
  List.fill(3)("hello") // List("hello", "hello", "hello")
  List.fill(2,3)('b') // List(List('b', 'b', 'b'), List ('b', 'b', 'b'))
  println(List.tabulate(5)(n => n * n)) // List(0, 1, 4, 9, 16)
  println(List.tabulate(5,5)((i, j) => i * j)) // List(List(0, 0, 0, 0, 0), List(0, 1, 2, 3, 4), List(0, 2, 4, 6, 8), List(0, 3, 6, 9, 12), List(0, 4, 8, 12, 16))
  List.concat(List(), List('b'), List('c')) // List(b,c)

