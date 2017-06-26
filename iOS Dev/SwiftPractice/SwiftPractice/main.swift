
import Foundation

//let is constant, var is variable
//variable assignment
print("Hello, World!")
let a = 10
print (a * 5)
let b = 55.2
let c : Double = 73
let test : String = "testing testing one two three"
print(test)

let d = "Hello World"
let e = 55
let f = d + String(e)

let apples = 15
let applesPhrase = "I have \(apples) apples"
print (applesPhrase)

let nickName : String? = nil
let fullName : String = "Mithell Scott Sandre"
let greetingPhrase : String = "Hello \(nickName ?? fullName)"
print (greetingPhrase)

//arrays
var firstArray = ["one", "two", "three", "four"]
var secondArray = [1,2,3,4]

for num in secondArray{
    print (num)
    if num > 2{
        print ("is greater than 2")
    }
}

// switch statements
let carName : String = "red truck"
switch carName{
case "green car", "red car":
    print ("nice green or red car")
case let x where x.hasSuffix("truck"):
    print ("Nice truck you got there")
default:
    print("none of the above")
}

//this is a dictionary
let interestingNumbers = [
    "Prime": [2, 3, 5, 7, 11, 13],
    "Fibonacci": [1, 1, 2, 3, 5, 8],
    "Square": [1, 4, 9, 16, 25],
]

var largest = 0
for (kind, numbers) in interestingNumbers {
    for number in numbers {
        if number > largest {
            largest = number
        }
    }
}
print(largest)

//while loops
var n = 2
while n < 100 {
    n *= 2
}
print(n)

var m = 100
repeat {
    print ("m is \(m) ")
    m *= 2
} while m < 100
print(m)

//for loop between two values
for i in 0..<10 {
    print (i)
}

//FUNCTIONS
func greeting(personName:String, dayOfWeek:String) -> String {
    return "Hello \(personName), what a lovely \(dayOfWeek) it is!"
}

print (greeting(personName: "Scott", dayOfWeek: "Monday")) // must pass arguments in order

func greeting2(_ person:String, on day:String) -> String { // _ means no argument label needed
    return "Hey \(person) how is your \(day) going?"
}

greeting2("Thomas", on:"Tuesday") //no argument label for person

func calculateStatistics(scores: [Int]) -> (min: Int, max:Int, sum:Int){
    var min = scores[0]
    var max = scores[0]
    var sum = 0;
    for i in scores {
        sum += i
        if i < min {
            min = i
        } else if i > max {
            max = i
        }
    }
    return (min, max, sum)
}

var someScores = [1,2,3,4,5,6,5,32,12313,-222, 2343]
var results = calculateStatistics(scores : someScores )
print("max is \(results.max)")
print(results.0)
print(results.1)
print(results.2)

//nested functions
func returnThirty() -> Int {
    var x : Int = 10
    func addTen(variable : Int) -> Int {
        return variable + 10
    }
    
    x = addTen(variable : x)
    
    func addTenMore(){ //functions have access to outer variables too!
        x += 10
    }
    addTenMore()
    
    return x
}
print("x is \(returnThirty())")

//functions are a first-class type. they can return another function as their its value

func makeIncrementer() -> ( (Int) -> Int){
    func addOne(number : Int) -> Int {
        return number + 1;
    }
    return addOne
}

var increment = makeIncrementer() //little confused about what this line does
print(increment(7))


//function can take another function as one of its arguments
func hasAnyMatches(list: [Int],_ condition: (Int) -> Bool) -> Bool { //takes in a function as its condition variable
    for item in list{
        if condition(item){
            return true
        }
    }
    return false
}

func numberLargerThanTen(value : Int) -> Bool {
    return value > 10
}

var numbers = [1,2,3,4,5,6, 11, 4]
print (hasAnyMatches(list : numbers, numberLargerThanTen)) // don't need to say 'condition : numberLargerThanTen' since I used _

//functions are a special case of closures; we can use them to call stuff at a later point in time

numbers.map({ (number: Int) -> Int in
    let result = 3 * number
    return number
})

for num in numbers {
    print (num)
}

//ex
let mappedNumbers = numbers.map({number in 3 * number})
print (mappedNumbers)

let sortedNumbers = numbers.sorted {$0 > $1}
print(sortedNumbers)

//===================Objects and Classes =======================

class Shape {
    let colour : String = "red"
    var numberOfSides = 0
    
    func simpleDescription() -> String {
        return "I am a shape with \(numberOfSides) sides"
    }
    
    func increaseNumSides(num : Int) {
        numberOfSides += num
    }
}

var firstShape = Shape()
firstShape.numberOfSides = 5
firstShape.increaseNumSides(num: 3)
print(firstShape.numberOfSides)
print(firstShape.simpleDescription())

//class with "initializer", basically just a constructor

class namedShape {
    var numberOfSides : Int = 0
    var name : String = ""
    init(name : String){ //use deinit as a destructor
        self.name = name
    }
    init(){
        self.name = "NoName"
    }
    func simpleDescription() -> String {
        return "I am a a shape named \(self.name) with \(self.numberOfSides) sides"
    }
}

class Square : namedShape {//inheritance
    var sideLength : Double
    
    init(sideLength : Double, name: String){
        self.sideLength = sideLength
        super.init(name: name)
        numberOfSides = 4
    }
    
    func area() -> Double {
        return sideLength * sideLength
    }
    
    override func simpleDescription() -> String {
        return "A squre with side length \(self.sideLength) and area \(self.area())"
    }
}

let testSquare = Square(sideLength: 5.2, name: "firstSquareName!")

print (testSquare.area())
print(testSquare.simpleDescription())

class EquilateralTriangle : namedShape {
    var sideLength : Double = 0.0
    
    init (sideLength : Double, name : String){
        self.sideLength = sideLength
        super.init(name: name)
        numberOfSides = 3
    }
    
    var perimeter : Double {
        get {
            return 3.0 * self.sideLength
        }
        
        set {
            sideLength = newValue / 3.0
        }
    }
    
    override func simpleDescription() -> String {
        return "An Equilateral Triangle with sideLength : \(self.sideLength) and perimeter \(self.perimeter)"
    }
}

var triangle = EquilateralTriangle(sideLength : 22.45, name : "firstTriangle")
print(triangle.perimeter)
print(triangle.sideLength)
print(triangle.simpleDescription())

//using willSet. I still don't know what didSet does...

class TriangleAndSquare {
    var triangle : EquilateralTriangle {
        willSet {
            square.sideLength = newValue.sideLength
        }
    }
    
    var square : Square {
        willSet {
            triangle.sideLength = newValue.sideLength
        }
    }
    
    init(size: Double, newName : String){
        square = Square(sideLength: size, name: newName)
        triangle = EquilateralTriangle(sideLength : size, name : newName)
    }
}

var triangleAndSquare = TriangleAndSquare(size : 3.14, newName : "new test shape")
print(triangleAndSquare.square.sideLength)
print(triangleAndSquare.triangle.sideLength)
//now change square to see if it changes triangle's side length also
triangleAndSquare.square = Square(sideLength : 55.2, name : "newTestSquare")
print("new square size: \(triangleAndSquare.square.sideLength)")
print("new triangle size: \(triangleAndSquare.triangle.sideLength)")

//use ? as kind of like try's, they evaluate what is before the ? and if it is NOT NIL then everything after the ? is applied to it
let optionalSquare : Square? = Square(sideLength : 5,name : "optional Square")
let sideLength = optionalSquare?.sideLength

print("let's see if optional square exists: optionalSquare sideLength : \(sideLength)")

//Enumerations and Structures
//enumerations can have methods and pararms? associated with them
//swift starts assigning values in order, starting at 0, unless you specify the value
enum Rank: Int {
    case ace = 1
    case two, three, four, five, six, seven, eight, nine, ten
    case jack, queen, king
    
    func simpleDescription() -> String {
        switch self {
        case .ace:
            return "ace"
        case .jack:
            return "jack"
        case .queen:
            return "queen"
        case .king:
            return "king"
        default:
            return String(self.rawValue)
        }
    }
}

let myAce = Rank.ace
let myAceRawValue = myAce.rawValue

print ("My aceRawValue is: \(myAceRawValue)")

let myKing = Rank.king
let myKingRawValue = myKing.rawValue
print ("My kingRawValue is: \(myKingRawValue)")

let myJack = Rank.jack
let myJackRawValue = myJack.rawValue
print ("MyJackRawValue is : \(myJackRawValue)")

//function comparer that compares two Rank value
func compareRank(_ firstVal : Rank,_ secondVal : Rank) -> Bool {//returns true if firstVal.rank is higher than second rank
    if firstVal.rawValue > secondVal.rawValue {
        return true
    }
    return false
}

print("comparing two ranks: king vs jack: is King higher? \(compareRank(myKing, myJack))")

//work backwards
if let convertedRank = Rank(rawValue: 3){
    let convertedDescription = convertedRank.simpleDescription()
    print("converted Description is: \(convertedDescription)")
}

enum Suit {
    case spades, hearts, diamonds, clubs
    func simpleDescription() -> String {
        switch self {
        case .spades:
            return "spades"
        case .hearts:
            return "hearts"
        case .diamonds:
            return "diamonds"
        case .clubs:
            return "clubs"
        }
    }
    
    func getColour() -> String {
        switch self {
        case .spades, .clubs:
            return "black"
        case .hearts, .diamonds:
            return "red"
        }
    }
}

let heartsCard = Suit.hearts
let heartsCardDescription = heartsCard.simpleDescription()
print (heartsCardDescription)
print(heartsCard.getColour())

enum ServerResponse {
    case result(String, String)
    case failure(String)
}

let success = ServerResponse.result("6:00 am", "8:00 am")
let failure = ServerResponse.failure("out of cheese")

switch success {
case let .result(sunrise, sunset):
    print ("Sunrise is at : \(sunrise) and sunset is at \(sunset)")
case let .failure(message):
    print ("Failure... \(message)")
}

//Structures --> these are passed by COPY, classes are passed by reference, so aren't classes faster?

struct Card {
    var rank: Rank
    var suit: Suit
    
    func simpleDescription() -> String {
        return "The \(rank.simpleDescription()) of \(suit.simpleDescription())"
    }
    
    func getDeck(){
        for i in 0..<52 {
            var thisRank = Rank(rawValue: i % 13)
            var thisSuit : Suit
            switch i % 4{
            case 0:
                thisSuit = Suit.hearts
            case 1:
                thisSuit = Suit.clubs
            case 2:
                thisSuit = Suit.diamonds
            case 3:
                thisSuit = Suit.spades
            default:
                print("ERROR")
            }
            //var newCard = Card(rank: thisRank, suit: thisSuit) //TODO
        }
    }
}

//let threeOfSpades = Card(rank: Rank.three, suit: Suit.spades)//could also just do .three, .spades respectively since it knows what class/enum type to expect
//
//threeOfSpades.getDeck()


//=========Protocols and Extensions================

protocol ExampleProtocol {
    var simpleDescription: String {get}
    mutating func adjust()
}

//Classes, Structs, and Enumerations can all adopt Protocols

class SimpleClass : ExampleProtocol {
    var simpleDescription: String = "A very simple class"
    var otherProperty: Int = 12345
    func adjust() {
        simpleDescription += "now 100% adjusted"
    }
}

var simpleA = SimpleClass()
simpleA.adjust()
let simpleADescription = simpleA.simpleDescription
print(simpleADescription)

/*
 Note: if we made a SimpleStructure structure, then we would need the word mutating before any functions
 We don't need "mutating" before any functions inside a Class since functions inside a class are already allowed to MODIFY THE CLASS
 */

//use Extension to add extra functionality to an existing type, like a class or an data type or a struct, etc

extension Int: ExampleProtocol {
    var simpleDescription: String {
        return "The number \(self)"
    }
    
    mutating func adjust(){
        self += 42
    }
}

print(7.simpleDescription)

enum PrinterError : Error {
    case outOfPaper
    case noInk
    case onFire
}

func send(job: Int, toPrinter printerName: String) throws -> String {
    if printerName == "Never Has Toner"{
        throw PrinterError.noInk
    }
    return "job sent :)"
}

//======Error Handling====== do and catch, use 'try' inside of the  do block
do {
    let printerResponse = try send(job : 123, toPrinter : "Never Has Toner")
    print (printerResponse)
} catch {
    print(error)
}

//you can use multiple catch blocks. you write after the "catch" statement just as you would write after a switch statement
do {
    let printerResponse = try send(job:11111, toPrinter : "ScottSandrePrinter")
    print("printer response is: \(printerResponse)")
} catch PrinterError.onFire {
    print("ERROR: on fire")
} catch let printerError as PrinterError {
    print("Printer Error is \(printerError)")
} catch {
    print(error)
}

//can you use TRY in variable assignment as a try and catch type of thing. if the operation throws an error, it will assign it the value of NIL, otherwise it will just be an optional

let printerResponse_01 = try? send(job: 123, toPrinter: "Never Has Toner")
let printerResponse_02 = try? send(job: 1232, toPrinter: "asdfasdf")
//print(printerResponse_01)
//print(printerResponse_02)

// use "defer" to write blocks of code that will be executed at the very end of the function
var fridgeIsOpen = false
var fridgeContent = ["milk", "apple", "orange", "banana"]

func fridgeContains(_ food : String, _ foodList : [String]) -> Bool {
    fridgeIsOpen = true
    defer { // will execute this at end of program
        fridgeIsOpen = false
    }
    let result = foodList.contains(food)
    return result
}

print(fridgeContains("asdf", fridgeContent))
print(fridgeContains("apple", fridgeContent))

//Genereics: let you use ?any? type of object/data inside of a function.. not sure about "any"

func makeArray<Item>(repeating item: Item, numberOfTimes: Int) -> [Item] {
    var result = [Item]()
    for _ in 0..<numberOfTimes { // _ since the variable name of the iteration does not matter
        result.append(item)
    }
    return result
}

func displayArray<Item>(array : [Item]){
    for i in 0..<array.count {
        print(array[i])
    }
}

var newArray = makeArray(repeating: "hello ", numberOfTimes: 10)
displayArray(array: newArray)

//use where right before the body to specify a list of requirements for the types to ahve


/*
 Topics to Look Up More: (read a bit, do 1 good example from each)
 - Collection Types
 - Control Flow
 - Functions
 - Closures
 - Classes
 - Optional Chaining
 - Initialization
 - Error Handling
 - Type Casting
 - Extensions
 */

//==================================== Collection Types =================================

// ============================== Collection Types #1: Arrays ===========================

//only arrays (unordered), sets(ordered, unique), and dictionaries(unordered, key to value associations)

var array1 = Array<Int>()//long way, creates an empty array
var array2 = [Int]() //short form

var array3 = Array(repeating: 0.0, count: 3) //initializes so array is [0.0, 0.0, 0.0]
var array4 = Array(repeating: 1.2, count: 7)
var array5 = array3 + array4 //joins them together

var array6 = ["hello", "world"] //creating an array using literals

//useful functions
/*
 array6.append()
 array6.isEmpty
 array6.count //size
 array6.capacity //total storage divided by element size capacity before it needs to be resized
 array6.insert()
 array6.remove()
 
 */

array6[0] = "blah"
array5[3...6] = [22, 22, 22] //can change multiple values


for i in array5 {//iterating over an array method 1 (like an iterator, goes through the values)
    print(i)
}

for (index, value) in array4.enumerated(){
    print("Index #\(index + 1) has value : \(value)")
}

// ============================== Collection Types #2: Sets ==============================
var letters = Set<Character>()
letters.insert("a") //so apparently a character is a String of length 1?
letters = []

var favouriteGenres : Set<String> = ["Rock", "Blues", "EDM"] //set type could also be inferred, don't need to say String
print ("I have \(favouriteGenres.count) favourite genres")
//insert, isEmpty, remove, contains, sorted

for genre in favouriteGenres.sorted() {//iterating over it
    print ("one of my favourite genres is \(genre)")
}

//---------->> Important: Performing Set Operations
//a.intersection(b), a.symmetricDifference(b), a.union(b), a.subtraction(b)

let oddDigits: Set<Int> = [1, 3, 5, 7, 9]
let evenDigits: Set = [0, 2, 4, 6, 8]
let singleDigitPrimeNumbers = [2,3,5,7,9]

oddDigits.union(evenDigits).sorted() // 1 - 9

//Set Membership
//isSubset(of), isSuperset(of), isStrictSubset(of) etc

// ============================== Collection Types #2: Dictionary ========================
var namesOfIntegers = [Int : String]()//key is of type integer, value is of type String
namesOfIntegers[16] = "sixteen"

//==================================== Functions  ========================================


func somefuntion(argumentLabel paramName: Int){//paramName is used inside of the function, argumentLabel is used when the function is CALLED
    
}

func anotherFunction(someValue : Int = 22){//the default value for the parameter is 22. It will be used if no value is passed to it
    
}

















