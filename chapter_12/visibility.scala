class Outer:
  class Inner:
    private def f = "f"
    class InnerMost:
      f // OK
  //(new Inner).f // error: f is not accessible

package p:
  class Super:
    protected def f = "f"
  class Sub extends Super:
    f
  class Other:
    val a = 3
    //(new Super).f
    // error: f is not accessible

package bobsrockets:
  package navigation:
    private[bobsrockets] class Navigator:
      protected[navigation] def useStarChart() = {}
      class LegOfJourney:
        private[Navigator] val distance = 100
  package launch:
    import navigation.*
    object Vehicle:
      private[launch] val guide = new Navigator
