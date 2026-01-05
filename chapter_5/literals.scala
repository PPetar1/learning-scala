import scala.language.postfixOps

@main def m() = 
  val hex = 0x00FF// Or 0X00ff
  val long = 35L// Or 35l
  val little: Short = 367
  val littler: Byte = 38

  val double = 1.234
  val bigger = 1.2345e1

  val float = 1.2345f

  val char = 'a'
  val charUnicode = '\u0041'
  val backslash = '\\'

  val string = "string"
  val rawString = """Somethingf :" /\

                     sadsajjdio
                     All of this is a single string"""
  val striped = """|Somethingf :" /\
                   | s
                   |sadsajjdio
                   |this string will lose the spaces before | in each row""".stripMargin

  // String interpolation
  val name = "reader"
  println(s"Hello, $name!")
  println(s"A ${6 * 7}")
  println(raw"No\\\\\escape!")// Provides interpolation but no escape chars
  println(f"${math.Pi}%.5f")// f strings support formatting

  val names = "something else"
  println(s"$names")// You cannot type $nameappended, probably you must use "$name" + appended

  val sum = 1.+(2)// All operators are methods
  println(names indexOf 'e')// Methods declared as infix can also be used like this, rest will give warrnings

  val num = -7// prefix notation; same as (7).unary_-; If you write them yourself only +, -. !, ~
              // are allowed
  val converted = 7 toLong// postfix notation; equivalent to 7.toLong or 7.toLong() (empty 
                          // brackets can be omitted but the convention is to leave them when 
                          // a method has side effects); In order to use postfix notation
                          // you must import scala.language.postfixOps

  val bool = true || (false && true)// && and || are lazy, just like in c
  val another = true | (false & true)// & and | are equivalent to && ||, but not lazy, the 
                                     // the expressions will be evaluated no matter what

  println(s"${1&2}")// bitwise and
  println(s"${1|2}")// bitwise or
  println(s"${1^2}")// bitwise xor
  println(s"${~1}")// bitwise invert
  
  // Shifts
  println(s"${-1 >> 31}")// Signed shift (fills with 1s)
  println(s"${-1 << 2}")
  println(s"${-1 >>> 31}")// Unsigned shift right (fills with 0s)

  // Comparisons
  //List(1,2,3) == "hello"// You can compare objects of different types
  //EDIT: You can't anymore; they patched it out

  null == "hello"// You can compare with nulls; if null is on the left the result is false
                 // if its on the right, it is passed to == method as an argument (which should
                 // probably return false)
  ("hel" + "lo") == "hello"// true; Scala compares value equality, this is true even if there are
                           // different objects being compared

//Given that Scala doesn’t have operators, per se, just a way to use methods
//in operator notation, you may be wondering how operator precedence works.
//Scala decides precedence based on the first character of the methods used in
//operator notation (there’s one exception to this rule, which will be discussed
//in the following pages). If the method name starts with a *, for example,
//it will have a higher precedence than a method that starts with a +. Thus
//2 + 2 * 7 will be evaluated as 2 + (2 * 7). Similarly, a +++ b *** c (in which
//a, b, and c are variables, and +++ and *** are methods) will be evaluated
//a +++ (b *** c)
//The one exception to the precedence rule, alluded to earlier, concerns
//assignment operators, which end in an equals character. If an operator ends
//in an equals character (=), and the operator is not one of the comparison
//operators <=, >=, ==, or !=, then the precedence of the operator is the same
//as that of simple assignment (=). That is, it is lower than the precedence of
//any other operator.
//When multiple operators of the same precedence appear side by side in
//an expression, the associativity of the operators determines the way operators
//are grouped. The associativity of an operator in Scala is determined by its
//last character. As mentioned on page 80 of Chapter 3, any method that ends
//in a ‘:’ character is invoked on its right operand, passing in the left operand.
//Methods that end in any other character are the other way around: They are
//invoked on their left operand, passing in the right operand. So a * b yields
//a.*(b), but a ::: b yields b.:::(a).
  








