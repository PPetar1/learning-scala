case class PosInt(value: Int):
  require(value > 0)
  export value.{<< as shl, >> as shr, >>> as _, *}

@main def m() =
  val x = PosInt(24)
  x + 2
  x shl 1
