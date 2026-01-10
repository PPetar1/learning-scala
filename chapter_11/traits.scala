// Traits are similar as in Rust from what i can tell, except you can use fields as well
// They are an alternative for multiple inheritance which is not present in Scala

trait Example:
  def ex = "A"

class Frog extends Example: // When using extends you implicitly inherit traits superclass
  override def toString = "green"
  override def ex = "B" // You can override traits members in the same way as usual

// class Frog extends Animal, Example, AnotherTrait // This is how you would inherit an Animal 
                                                    // superclass and multiple traits as well

// By just mixing the trait Ordered for Rational and defining the compare method you can 
// use <, >, <=, >= on members of this class. compare should return > 0 if this > that; 
// = 0 if this == that; and < 0 if this < that
class Rational(n: Int, d: Int) extends Ordered[Rational]:
  // ....
  val nom: Int = n
  val denom: Int = d

  def compare(that: Rational) = 
    (this.nom * that.denom) - (that.nom * this.denom)

// Beware that the Ordered trait does not define equals for you, because
// it is unable to do so. The problem is that implementing equals in terms of
// compare requires checking the type of the passed object, and because of type
// erasure, Ordered itself cannot do this test. Thus, you need to define equals
// yourself, even if you inherit Ordered. You can find out how to go about this
// in Advanced Programming in Scala.

// Traits as stackable modifications

abstract class IntQueue:
  def get(): Int
  def put(x: Int): Unit

import scala.collection.mutable.ArrayBuffer

class BasicIntQueue extends IntQueue:
  private val buf = ArrayBuffer.empty[Int]
  def get() = buf.remove(0)
  def put(x: Int) = buf += x

trait Doubling extends IntQueue: // This means that the trait can only be used by subclasses of
                                 // IntQueue

  abstract override def put(x: Int) = super.put(2 * x)
// Funny thing is that the trait has a super call on a method
// declared abstract. Such calls are illegal for normal classes because they will
// certainly fail at run time. For a trait, however, such a call can actually suc-
// ceed. Since super calls in a trait are dynamically bound, the super call in
// trait Doubling will work so long as the trait is mixed in after another trait or
// class that gives a concrete definition to the method.
// This arrangement is frequently needed with traits that implement stack-
// able modifications. To tell the compiler you are doing this on purpose, you
// must mark such methods as abstract override. This combination of mod-
// ifiers is only allowed for members of traits, not classes, and it means that
// the trait must be mixed into some class that has a concrete definition of the
// method in question.  

class MyQueue extends BasicIntQueue, Doubling

trait Incrementing extends IntQueue:
  abstract override def put(x: Int) = super.put(x + 1)

trait Filtering extends IntQueue:
  abstract override def put(x: Int) = 
    if x > 0 then super.put(x)

// Trait parameters
trait Philosophical(message: String):
  def philosophize = message

class Duck extends Philosophical("I quack, therefore I am!")

@main def m() =
  val frog = new Frog
  println(frog.ex) // You can use traits members from the class itself

  val ex: Example = frog // Trait also defines a type which can be used like so
  println(ex.ex)
  
  val queue = new MyQueue
  queue.put(10)
  println(queue.get()) // Returns 20 since MyQueue mixes in Doubling
  
  val queue1 = new BasicIntQueue with Doubling // You can also do this which is pretty nice
  queue1.put(20)
  println(queue1.get()) // 40

  // Multiple traits can be mixed in; They apply in order from right to left
  val queue2 = new BasicIntQueue with Doubling with Incrementing with Filtering
  queue2.put(-1)
  queue2.put(2)
  println(queue2.get()) // (2 + 1) * 2 = 6; (-1 + 1) * 2 = 0 so it got filtered out
