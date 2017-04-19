========================= Operating System Stuff =========================
	- what is an operating system: manages execution of apps, resources, CPU, memory
	- what is in RAM?
		1. run time environment, how does it actually run/work
		2. user program
		3. user data and storage
			- FREESTORE (HEAP) -> stores dynamic variables (new, malloc, calloc, pointers)
			- STACK (where everything else goes) (direct instantiation)


========================= end of Operating System Stuff ==================

========================Unix History======================================

- Unix, C develloped in early 1970s at Bell Labs/AT&T
- GNU (Gnu's Not UNIX) -> truly free, avialbe for everyone, anyone can customize it (in the 1980s)
	- gnu contains gcc, g++, bash, ls, rm, chmod, etc
- shell variable PATH: uses this to search for external programs, in order
- File Permissions: User, Group, other
	- chgrp (change gorup permissions)
	- chmod (change permissions of a file)

EX:
test.cc - AAA BBB CCC
AAA is user Permissions
BBB is group Permissions
CCC is other Permissions

grep:search for stuffe

======================== End of Unix History =========================

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

C++ Arrays:
	- declared statically
		int A[5];
	- declared dynamically
		int * A = new int [N]
		delete []A;
	- STD:: array
	- compile-time fixed-size array
	array<string,12> monthName = {"jan", ..., "dec"}

Abstract Data Type (ADT) vs Data Strutures:
	- ADT: should be understandable by looking only at the API (stack, queue, deque, map, etc)
	- DS: connotes an idea of an underlying implementation (vector, linked list, trees, hash table)


Queue:
	- initQueue
	- enter
	- leave
	- first
	- isEmpty
	- nuke

Stack:
	- initStack
	- isEmpty
	- push
	- pop
	- peek
	- nuke

BST:
	- at most TWO children
	- each node has a "key" value, we assume no duplicate keys
	- BST Property Holds for every node in the tree:
		- the keys of all nodes in the LEFT subtree are < my key
		- the keys of all nodes in the RIGHT subtree are > my key
	- loopup, insert, print, delete, init, isEmpty

Dictionary:
	- collection of ordered pairs (key, value)
	ex: map<string, integer> uniNum;
	- add(key, value)
	- overwrite(key, value)
	- lookup(key)
	- remove(key)

Map:
	- map<T1, T2> m; //T1 is the key field, T2 is the value field

HashTable:
	- constant time for most functions
	- CLOSED HASHING: fixed number slots, when 0.8x full, resize
	- OPEN HASHING: each bucket is actualy a pointer to a linked list

	HashTable (List of Lists) or (Regular):
		initHT(table, int k)
		myHash(int key, int numBuckets)
		nukeHT
		insertHT
		lookupHT
		printHT

Deque: ("deck")
	- double ended queue, similar to vectors, but allows fast insertion/deletion at BEGINNING and END
	- Idea 1: circular buffer of objects (NO)
	- Idea 2: circular buffer of object pointers
		- set of fixed-length arrays ("CHUNKS"), plus a master dynamic array (vector) of pointers (a circular bufer) that point to those chunks

Associative Containers:
	- ordering of the elements is based on a key value, NOT on the order of insertion
		- ex: employee records are sorted by SIN or NAME, not by order
		like: map<T1, T2> m; ; T1 is the KEY FIELD, it must support operator <


OOP:
	- Recall: Procedural Programming, Object Based Programming (no inheritance), and then OOP
	- some classes never have instances of them, they exist only to define common shapes of descendant classes
		- these ones are called Abstract Base Classes (ABC)
	- need to declare ALL methods inside the class definition
	- everything is passed by reference by default now
	- Lookup Order: local variable, then parameter, then is it a field of a defining class, then is it an inheritance ancestor

	Class Declaration vs Definition:
		- declaration specifies its SHAPE (what data sub parts it has, what it inherits from, private or protected or public etc)
		- definitions then define all of these things AFTER their declaration

	Class Initializers:
		- uses ONLY for constructors
		ex:
			//JAVA ish way
			Balloon::Balloon(string colour){
				this->colour = colour;
			}
			//C++ way
			Balloon::Balloon() : colour("transparent"){} // assignent to colour the string "transparent"
			OR
			Balloon::Balloon(string col) : colour(col) {}
	Member Variable:
		- can be an instance variable (one per instance of the class)
		- can be a class/static variable (one per class, ever)
	Member Method:
		- instance method (one per instance)
		- static method (one per class)

	Copy Constructor: creates a copy (clone) of existing object, puts it into new object
	C::C(const C & c) ; // C is the class name

	Accessor: reponrts on a value of the object; does not change it
	Mutator: may change  the value of an object

	Static Type: type it was declared to be
	Dynamic Type: type of the object it is currently pointer to, or null pointer
	Child class does NOT inherit the constructors and destructors of the parent

	Important from last Lecture:
	Parent * mike;
		//make mike point to an object that is a Parent or inheritnace descendant of parent
		//parent also has Parent::m()
		Parent * mike;
		mike->m();
			- if Parent::M is NOT virtual, this will always call Parent::m()
			- if Parent::m IS virtual, find out what class of object, call it C, mike points to right now
				- if C defines its OWN version of m, it will call C::m()
				- if C doesn not define its own version, it will walk up the inheritance hierarchy to find the most recent class where m() was given a definition


Review: Static VS Dynamic TYPING:
		- every pointer has a static type, which is its DECLARED type
		- the static type does not change during program execution
		- every pointer also has a DYNAMIC type, which is the type of object that it is currently pointer to (or nullptr)
			- during execution, a pointer's dynamic type might change whenever it is assigned a new value
		- a pointer can be set to point to an object of ANY SUbCLASS of its declared type


Iterator:
	for (vector<string>::iterator vi=v.end()-1; vi!=v.begin(); vi--) { // Stops one too early cout << (*vi) << endl;
		cout << (*vi) << endl;
	}

	AUTO:

	vector<string> v; //
	for (auto vi=v.cbegin(); vi != v.cend(); vi++){

	}

Some useful STL Algorithms:
	// most take parameters (container.begin(), container.end(); what you want it to do (value))
		- find: locates the first element that matches a given object
		- count: counts the number of elements that matches a given object
		- for_each: applies a function to each element
		- remove:
		- replace:
		- sort:
		- unique: removes adjacent identical elements (only useful if container is sorted)
		- min_element, max_elment
		- nth_element
		- random_shuffle
		- next_permutation** //Can cycle through all N! permutations of an ordered container!

Generics and Generic Functions/Classes:
	- we can supply the types of parameters when we use it
	- swap: T must support assignment
	- print: T must support <<
	EX:
		template<typename T>
		void anySwap(T &x, T & y){ // T is a place holder for some other type
			//type T must support operator =
			const T temp = x;
			x = y;
			y = temp;
		}

Adapter Design Pattern:
	- we have data structures that implement what we want, but the API terminology doesn't best fit what it should be for our usage
	Adapter Solution:
	1. define the API you really want (Stack: push, pop, etc)
	2. Instantiate (do NOT inherit) an object from the "workhorse" class that will do all of the heavy lifting,
		- probably a private member
	3. define the appropriate operations as fairly trivial operations using the workhorse class
		- called "forwarding" or "delegating"

Operator Overloading:
	- really useful (complex number class could redefine +, x, -, /)
	- can 1) make it a member of the class, or 2) overload the global definition of the operator
	best is 2:
		use:
			friend bool operator==(const Money& m1, const Money& m2); (in the CLASS declaration)
		then outside the class
			bool operator== (const Money& m1, const Money& m2)

	3 Basic Rules of Operator Overloading in C++ from StackOverflow:
		1. whenever the meaning of an operator is not obviously clear, do NOT Overload it
			- us a well-chosen method with a good name
		2. always stick to the operator's well known semantics
		3. always provide all out of the set of RELATED operations too

Separate Compilation:
	1. compile each of the compilation units (the .cc files)
				g++ -c Foo.cc //produces a new file, Foo.oo
	2. link the object files (.o files) into a single executable
				g++ -o myProgram *.o

	g++ -c Foo.cc //what happens here?
		1. preprocessor runs, performing #include, #define, and checks #ifdef #ifndef
		2. then, template instantiation (any classed defined using templates are made)
		3. then, put into an object file (.o)


Random Stuff:
	- pass by reference (&a); doesn't pass it a copy, acts like a pointer, but we use it as though it were the variable itself!
	- Global Variables: useage is frowned upon, defined outside the enclosing function/struct/class
	- Local Variables: are defined within a function/method body; come into existance when the function is called, and die when the function is terminated
	- Member/Instance Variables: sup part of a class; created when the instance is created; die when the instance dies
	- reference vs reference parameters: parameters are passed as parameters to a method (makes sense); regualar references are like Employee & e = EmployeeList.at(i)

			A* x = new A();     //Constructor
			A y = *x;           //Copy Constructor
			*x = y;             //Copy assignment operator
			delete y;           //Destructor
	- static variables must be defined separately from their declaration

Terms:
	- template method design patterns
	- STL (standard template library)
	- SOLID (design principles of OOP)
		- Single Responsibility: each class should have a single functional responsibility within the design of the problem
		- Open/Closed: should be open for extension but closed to modification
			- subclasses can add new and related features; cannot override or remove existing functionality
				- but overriding abstract methods is fine
		- Liskov's Principle of Substitutability (PoS):
			- need to be able to conceptually replae nay instance of a parent with an instance of the child
			- it should make sense to do so, even if it is not atually possible
			- parents are GENERALIZED versions of the children
			- children are specialized versions of their parents
		- Interface Segregation:
			- split larger interfaces into a set of smaller interfaees that model different roles of use
			- don't want classes growing too big
		- Dependency Inversion Principle
			- details should depend on abstractions
			- higher level stuff should not depend on lower level stuff
	- forwarding
	- delegating;
	- list (Plain, old doubly linked list)
	- anti-reflexive (A is not less than A)
	- Anti-symmetric (A < B implies b is not less than or equal to a)
	- transitive  (a < b, b < c ==> a < c)
	- library uses this test to see if a == b;
	 		if (!(a < b) && !(b < a))
	- identifier (name we have picked for a variable or for a procedure)
	- activation record (stack frame): area of storage on the run-time stack for when program enters a new scope
	- lazy evaluation/short circuit evaluation: will not test the second part of a comparison operation (AND, OR) if the first one makes it impossible or makes it already true
	- Principle of Least Astonishment:
		- API elements should not do surprising things
