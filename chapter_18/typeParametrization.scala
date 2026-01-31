// Functional queue
class Queue[+T] private (// Private here hides the class constructor 
  private val leading: List[T],
  private val trailing: List[T]
):
  private def mirror = 
    if leading.isEmpty then
      new Queue(trailing.reverse, Nil)
    else
      this

  def head = mirror.leading.head
  def tail = 
    val q = mirror
    new Queue(q.leading.tail, q.trailing)
    def enqueue[U >: T](x: U) = // Here we say that we can enqueue any element U that is a 
                                // supertype of T and get a resulting Queue[U] (we declare a 
                                // lower bond)
                                // By making this change we can declare Queue to use +T 
                                // because we can be sure that no errors will occur if we try
                                // to add an element to a supertype of Queue[T]
                                // Compiler uses some fliping rules that i didn't fully
                                // understand, ref: 18.4 Checking variance annotations p.405
// We can also declare upper bounds with T <: U

    new Queue[U](leading, x :: trailing)

// Hiding the implementation with a factory method
object Queue:// Companion object of our class
  def apply[T](xs: T*) = new Queue[T](xs.toList, Nil)

// Alternative is to use traits and an inner implementation class
// trait Queue[T]:// Here we can write +T if we want Queue to be covariant in T or -T if we want
                  // it to be contravariant (covariant means that Queue[String] would be a
                  // subtype of Queue[AnyRef], and contravariant is reverse so Queue[AnyRef] 
                  // would be a subtype of Queue[String]) 
//   def head: T
//   def tail: Queue[T]
//   def enqueue(x: T): Queue[T]
// object Queue:
//   def apply[T](xs: T*): Queue[T] =
//     QueueImpl[T](xs.toList, Nil)  
//   private class QueueImpl[T](
//     private val leading: List[T],
//     private val trailing: List[T]
//   ) extends Queue[T]:
//     def mirror =
//       if leading.isEmpty then
//         QueueImpl(trailing.reverse, Nil)
//       else
//         this
//     def head: T = mirror.leading.head
//     def tail: QueueImpl[T] =
//       val q = mirror
//       QueueImpl(q.leading.tail, q.trailing)
//     def enqueue(x: T) =
//       QueueImpl(leading, x :: trailing)

@main def m() =
  val a = Queue(List(1,2,3,4))
