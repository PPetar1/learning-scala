package bobsrockets:
  package navigation:
    // In package bobsrockets.navigation
    class Navigator
    package launch:
      // In package bobsrockets.navigation.launch
      class Booster

// Accesing parent packages without prefix
// package bobsrockets:
//   package navigation:
//     class Navigator:
//       // No need to say bobsrockets.navigation.StarMap
//       val map = new StarMap
//     class StarMap
//   class Ship:
//     // No need to say bobsrockets.navigation.Navigator
//     val nav = new navigation.Navigator
//   package fleets:
//     class Fleet:
//       // No need to say bobsrockets.Ship
//       def addShip = new Ship
// 
// Parent package is not accesible if you don't enclose within it
// package bobsrockets:
//   class Ship
// package bobsrockets.fleets:
//   class Fleet:
//     // Doesn't compile! Ship is not in scope.
//     def addShip = new Ship
// 
// Accessing shadowed package names
// // In file launch.scala
// package launch:
//   class Booster3
// 
// // In file bobsrockets.scala
// package bobsrockets:
//   package launch:
//     class Booster2
// 
//   package navigation:
//     package launch:
//       class Booster1
//     
//     class MissionControl:
//       val booster1 = new launch.Booster1
//       val booster2 = new bobsrockets.launch.Booster2
//       val booster3 = new _root_.launch.Booster3
