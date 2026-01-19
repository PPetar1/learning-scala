import simulation.*

class Time: 
  var hour = 12 // When you declare a var in a class you auto get getter and setter for the field
  var minute = 0

@main def m() = 
  var time = new Time
  println(time.hour)
  time.hour_=(15)
  println(time.hour)
  time.hour = 3
  println(time.hour)

  object MySimulation extends CircuitSimulation:
    def InverterDelay = 1
    def AndGateDelay = 3
    def OrGateDelay = 5

  import MySimulation.* 

  val input1, input2, sum, carry = new Wire

  probe("sum", sum)
  probe("carry", carry)
  
  halfAdder(input1, input2, sum, carry)

  input1 setSignal true
  run()
  input2 setSignal true
  run()
