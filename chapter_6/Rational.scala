class Rational(n: Int, d: Int):
  //println("Created " + n + "/" + d)// This will be added to primary constructor
  require(d != 0)// Again this will be appended to constructor, i guess it just throws an error

  private val g = gcd(n.abs, d.abs)// This will be set first so we can use it for numer and denom
  val numer: Int = n / g
  val denom: Int = d / g

  def this(n: Int) = this(n, 1)// auxiliary constructor;first statement must be a call to another
                               // constructor (this(...))

  override def toString = s"$numer/$denom"
  
  def +(that: Rational): Rational =
    Rational(
      numer * that.denom + that.numer * denom,
      denom * that.denom
    )

  def +(i: Int): Rational =
    Rational(numer + i * denom, denom)

  def *(that: Rational): Rational = 
    Rational(numer * this.numer, denom * this.denom)

  def *(i: Int): Rational =
    Rational(numer * i, denom)

  def lessThan(that: Rational) =
    this.numer * that.denom < that.numer * this.denom

  def max(that: Rational) = 
    if this.lessThan(that) then that else this// Here first this can be ommited but second isn't

  private def gcd(a: Int, b: Int): Int =
    if b == 0 then a else gcd(b, a % b)

extension (x: Int)
  def +(y: Rational) = Rational(x) + y
  def *(y: Rational) = Rational(x) * y
