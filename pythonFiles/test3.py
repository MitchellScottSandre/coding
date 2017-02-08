def addTwoNums(x, y):
    return x + y

myList = []
myList.append(1)
myList.append("what is going on")

for x in myList:
    print x

num = 77
print "Number is %d" % num

print "Enter a number:"
n = (int) ( raw_input() )
for x in xrange(n):
    print x

a = 10
b = 100
print "%d + %d is %d" % (a, b, addTwoNums(a, b))
