========================= Operating System Stuff =========================
	- what is an operating system: manages execution of apps, resources, CPU, memory
	- what is in RAM?
		1. run time environment, how does it actually run/work
		2. user program
		3. user data and storage
			- FREESTORE (HEAP) -> stores dynamic variables (new, malloc, calloc, pointers)
			- STACK (where everything else goes) (direct instantiation)


========================= end of Operating System Stuff ==================

String Stuff:
	s.length()
	s.at(i)
	s[i]
	s.substr(i,n)
	s.find(str, pos)
	s.rfind()
	s.replace()
	s.insert()

Input and Output:
	cin, cerr, cout
	cin.eof()
	//these do not return true until you go one step TOO FAR
	getline(cin, line)

Vectors:
	- have both a size and capacity
	- capcaity: number of elements it has room for
	- size: number of active elements
	- push_back()
