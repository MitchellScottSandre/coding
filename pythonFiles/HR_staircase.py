n = raw_input()
n = (int) (n)
for r in range(n,0,-1):
	print " " * (r - 1) +  "#" * (n + 1 - r)
	