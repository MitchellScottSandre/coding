print "Hello World"
x = 5
myInt = 7
myFloat = 7.0
myFloat2 = float(7)
hello = "hello"
world = "world"
lotsOfHellos = hello * 10
print "Lots of hellos using the * 10 operator gives " + lotsOfHellos
helloworld = hello + " xxx " + world
print helloworld
print "my int is %d" % myInt
#print "my int is also" + myInt # this does not work!!
#isInstance(variablename, dataType)
#if myString == "hello":
print "x is %d" % x

myIntSquared = myInt ** 2
print "myInt squarred is %d" %myIntSquared

mylist = []
mylist.append(1)
mylist.append(2)
mylist.append(3)
print(mylist[0]) # prints 1
print(mylist[1]) # prints 2
print(mylist[2]) # prints 3

# prints out 1,2,3
for x in mylist:
    print x

evenNumbers = [2,4,6,8]
oddNumbers = [1,3,5,7]
allNumbers = oddNumbers + evenNumbers
moreEvenNumbers = evenNumbers * 3

#==================STRING FORMATTING===================
name = "Scott"
print "Hello, %s. Hope you are having a great day!" % name
age = 19
print "Hello, %s. You are %d years old." % (name, age)
print "This prints out evenNumber list: %s" % evenNumbers
# %s String, %d integers, %f floating points, %.<number of digits>f

#================Basic String Operations===============
movie =  "The Godfather Part II"
print "length of movie string is %d and its name is %s" % (len(movie), movie)
print "The index of the letter f is %d" %movie.index("f")
print "The number of letter ts in the movie string is %d" %movie.count("t")
print "A slice of the string from index 2 to 11 is " + movie[2:11]
print "This prints the string backwards [::-1] " + movie[::-1]
movie = movie.upper()
print movie + "... just used movie = movie.upper(). now doing movie = movie.lower()"
movie = movie.lower()
print movie
print movie.startswith("the") 
print "..just did movie.startswith('the')"
print movie.endswith("cat")

#==================Conditions ==========================

a = 2
print "Doing conditions now..."
print x == 2 #prints out True
print x == 3
print x < 3

print "Boolean operators... 'and', 'or'...if statements..end with :"
name = "Scott"
age = 19
if name == "Scott" and age == 19:
	print "Your name is %s and your age is %d" %(name, age)
if name == "John" or name == "Scott":
	print "Your name is either Scott or John"
#the in operator
print "the in Operator..used to check if a specified object exists within an iterable object container, such as a list"
namesList = ["Scott", "Bob", "Rick"]
#remember to end if statements with :
if name in namesList:
	print "your name is in the names list"

#===============IF and ELIF condition
if age < 10:
	print "age is less than 10"
elif age < 20:
	print "age is less than 20"
else:
	print "age is greater than or equal to twenty"
# ================== the 'is' operator ===========
# the '==' equals operator matches values of variables.
# the 'is' operator matches instances 
x = 5
y = 5
print "comparing x == y to x is y..."
print x == y
print x is y
print "lol what just happened...that should be false."
#the "not" operator...not before a boolean expression inverts it
print "printing not False should print true"
print not False

#========================loops=======================
numbers = [1,2,3,4,5,6,7,8,9,10]
for x in numbers:
	print x
#get to call the variable whatever you want, remember the : symbol
#loops can iterate over a sequence of numbers using the "range" and
#"xrange" functions. rnage returns a new list with numbers of that
#specified rannge, whereas xrange returns an iterator, which is more efficient
print "using xrange(6)...remember that it is zero based"
for x in xrange(6):
	print x

print "using xrange (3, 6)..."#3 4 5 
for x in xrange(3,6):
	print x

#while loops
print "using a while loop now..."
count = 0
while count < 5:
	print count
	count += 1

#break exits a for loop or while loop, continue is used to skip the current block
#can use else for loops...when the loop condition of the "for" or "while"
#statement fails then code part in else is executed
print "using while loop with an else clause"
count = 0
while count < 5:
	print count
	count += 2
else:
	print "count value reached %d" %count