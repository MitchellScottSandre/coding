var str = "Hello";                // local variable, when inside a function
str2 = "Hello World";             // global variable in default context (window.str2)
str3 = 'My quote char: " ';       // single or double quote
str4 = "My really really really \
really long string broken into \
multiple lines";

var newObject = new Object();     // constructor
newObject = {};           // shorthand for same
newObject.name = "bob"            // dynamic attributes
newObject.name = null         // it's there (null item)
delete newObject.name         // it's gone (undefined)
newObject["real age"] = 33;       // array notation/hash table

var obj = {           // create object using JSON
    name: "Bob",          //   aka Javascript Object Notation
    details: {
        age: 33,
        "favorite color": "green"
    }
}

obj.name
obj.details["favorite color"]

var newArray = [];                // no size
newArray[3] = "hi";               // grows dynamically
newArray[2] = 13;                 // any type
newArray.push(newObject);         // add new item
newArray.pop();               // remove it

/* javascript types */
typeof("string") == "string"
typeof(3) == typeof(3.4) == typeof(0x34) == "number"
typeof(myObject) == typeof(myArray) == "object" // arrays are objects
typeof(true) == typeof(1 == 2) == "boolean"
typeof(Math.sin) == "function"
typeof(notthere) == "undefined"

/* comparisons */
123 == "123"                     // true => converts type
123 === "123"                    // false => checks type
typeof(x) == "undefined"     // x isn't there
x == null            // x is defined, but null

/* Numbers */
parseInt("123")          // base 10 => 123
parseInt("123", 16);         // base 16 => 291
parseFloat("123.43");        // 123.43

isNaN(0/0) == true       // illegal number
3/0 == Infinity          // legal...
-3/0 == -Infinity        //
isFinite(3/0) == false       // ... but not finite

/* regular expression (regex) string comparisons */
matches = "hello".match(/h../)   // returns array ["hel"] or null

re = new RegExp("h..", "ig");    // construct regexp -- no slashes
matches = "hello".match(re);     // use it

"hello".replace(/h/,"b")     // => "bello"



if (str == "Hello"){    // if-else
  alert("Hi");      // popup dialog
}
else{
  alert("something is wrong!");
}

a = 3, b = 4;       // multi-assigment
c = a > b ? a : b;  // c gets bigger item (b)

switch (name){      // switch statement
  case "Bob":
    alert("Hi Bob!")
    break
  case "Joe":
    alert("Hey Joe.")
    break
  default: alert("Do I know you?")
}

while (i < n){          // the basics
 // do something
 i++;
}

for (var i=0; i<n; i++){
  // do something else
}

for (var key in obj){
  // do something with obj[key]
}

function foo(a,b){          // global function
  return a + b;
}

var fn = function(a,b){     // save function as variable...
  return foo(a,b);
}

obj.fn = function(a,b){     // ...or as part of object
  return a + b;
}

function bar(a,b){
    var n = 1;                  // local var

    function helper(x) {            // inner function...
        return 1/Math.sqrt(x + n);  // .. can use local vars
    }
    return helper(input);           // avoid need for global function
}

foo(1,2) == fn(1,2) == 3;   // true


function Person(first, last) { // create "constructor"
    this.first = first;        // public variables -- reference current object
    this.last = last;

    var privateFn = function(first, last){  // private function
    // ...
    }

    this.setName = function(first, last){ // public function
        this.first = first;
    this.last = last;
    }

}

Person.prototype.fullName = function() { // extend prototype
    return this.first + ' ' + this.last; //   even at runtime!
}

var bob = new Person("Bob", "Smith"); // "new" creates an object
bob.fullName();               // => "Bob Smith"
