import org.stairwaybook.expr.*

object Express:
  def main(args: Array[String]): Unit = 
    val f = new ExprFormatter
    
    val e1 = BinOp("*", BinOp("/", Num(1), Num(2)),
                        BinOp("+", Var("x"), Num(1)))
    val e2 = BinOp("+", BinOp("/", Var("x"), Num(2)),
                        BinOp("/", Num(1.5), Var("x")))
    val e3 = BinOp("/", e1, e2)
    val e4 = BinOp("+", Num(2), BinOp("+", Num(3), Num(1))) // Bug in the book - writes 2+(3+1)
    def show(e: Expr) = println(s"${f.format(e)}\n\n")

    for e <- Vector(e1, e2, e3, e4) do show(e)
