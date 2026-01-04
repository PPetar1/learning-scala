@main def m() = 
  val oneTwoThree = List(1, 2, 3) // List is an alternative to Array with a diff that its elements are immulable
  val oneTwo = List(1, 2)
  val threeFour = List(3, 4)
  val oneTwoThreeFour = oneTwo ::: threeFour// This creates a new list, oneTwo and threeFour are still untouched and any "changes" to oneTwoThreeFour will just create a new list, neither of the two originals will be touched

  val oneTwoThreeNew = 1 :: 2 :: 3 :: Nil// Nil is short for empty list
                                         // operators that end in : are envoked on right operand
                                         // so 3 :: Nil will be evaluated as Nil.::(3)
                                        
