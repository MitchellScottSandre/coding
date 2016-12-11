
a = map(int,raw_input().strip().split(' '))
b = map(int,raw_input().strip().split(' '))
aCount = 0
bCount = 0
for i in range(3):
	if a[i] > b[i]:
		aCount += 1
	if b[i] > a[i]:
		bCount +=1
print "%d %d" % (aCount,bCount)