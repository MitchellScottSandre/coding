#Functions
def my_function():
	print "Hello from my function"

def printForwardsThenBackwards(word):
	print word + " .. " + word[::-1]

def sumTwoNums(a, b):
	return a + b

my_function()

name = "scott sandre"
printForwardsThenBackwards(name)
x = 10
y = 20
z = sumTwoNums(x, y)
print z

print "testing input...."
str = raw_input("Enter your input: ")
print "You just entered: " + str

str2 = input()
print str2


#Int(raw_input())