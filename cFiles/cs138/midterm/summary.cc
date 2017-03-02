error example:
cout << "m" + "g" << endl; //c++ converts this to a char* because it assumes you probably meant to work in c

therefore, to add two strings with + use
cout << (string)"m" + (string)"g" << end;

getline(cin, line);

3. Input is taken from (existing file) file myinput.txt instead; output shows up on screen
./myprog < myinput.txt

4. reads in from file and also outputs to desired file too
./myprog < myinput.txt > myoutput.txt

File Permissions and UNIX Command Line Stuff????

File Permissions: ========================================================================================
Each file/dir in Unix has three sets of access permissions that define how each of three different classes of users may access it:

User: the userID that owns the file
Group: the Unix group associated with the file
Other: everyone else
•  A Unix group is an "arbitrary" collecIon of userIDs that make sense in some organizaIonal context
–  Groups are defined in a single place under admin (root) control
–  A user can belong to mulIple groups
e.g., research group, ad hoc project, top secret project, bowling league scores, ...
–  We won't worry too much about groups, ... in pracIce, group permissions for a file are usually the same as "other"

r read
w Write
x execute

To see the permissions, do "ls –l":

drwxr-x---
drwxr-x--- 2
-rw------- 1 1 jfdoe jfdoe 63332 Oct 21 08:50 notes.tex

chgrp
- change group name associated with file
chgrp [ -R ] group-name file/directory-list
[ - R ] recursively modify for the rest of the directory

chmod:
- change permissions of a file
chmod [-R] mode-list file/directory-list

where mode-list has the form
	Security-level Operator Permission
	security level: u for you (user), g for group, o for other, a for all
	operator: + for add Permission
	operator: - for remove Permission

ex: chmod [-R] u+r
multiple permissions
chmod u+r,g+x filename
chmod u-rx filename


PERMISSION INFORMATION IS:
d is directory, - is file
then user permissions
then group permissions
then other permissions
(d or -)  rw- --- ---

columns: permissions, #-of-hard-links (ignore this), owner ID, group ID, file size in bytes (ignore for directories), date of last change, file name
Everything is a file (surprise)
Each file has 3 sets of permissions, User, Group, other

Read: can see it
Write: can add or delete files
execute: can cd there and do stuff

Find:
find searches for files/dirs within a directory hieratchy, according to various states critera
find [dir-list][expr]
options: -name pattern restricts file names to globbing pattern

Grep (one of our best friends)
regular expression matching

grep pattern, list a bunch of files, and it tells you call of the places where that pattern occurs

cat / less: print files to terminal window
	cat file-list
	less file-list

	ssh: secure shell, safe, encrypted, remote-login between clients and server host

	diff / cmp: compare 2 files looking for differences
		diff x y
			for file x and file y

	find: searches for files/dirs within a directory
		$ find -name "t*" # Important: Why quotes?
		./test.cc
		./testdata
		./oldTests/test-y2k-dir

	grep and egrep
		egrep: adds funky extra poweful pattern matching that is really useful sometimes, nut its slower on big searcheas as it has to do more work
		fgrep: faster, less reliable
		egrep: search and print lines matching pattern in findMostWordsPerLine_PassToFormatter_CreateAllLinesArray_thenCallDisplayFunction
		egrep usage:
			egrep [-irnv] pattern-string file-list

			egrep example: list lines contain "main" in files with suffix ".cc"
			$ egrep main *.cc # why no quotes?
				q1.cc:int main() {
				q2.cc:int main() {


Heap and Stack:========================================================================================

	Heap/Freestore:
		"new", "malloc", "calloc"
		referenced by pointers
		if it has a pointer, probably on heap
	Stack:
		- every time program is started, gets added to stack
		- when it completes, it gets removed from stack
		- will grow as you add functions

		strctName s;
		s.x = 100;
		s.y = 200; //stored in the STACK

		strctName *sPtr = new strctName; // empty constructor, DON'T NEED TO INCLUDE BRACKETS (but you can)
		sPtr->x = 100;
		sPtr->y= 200; //stored in HEAP
		//do cool shit with sPtr
		delete sPtr;

		Never point UP THE STACK with a pointer (could get removed then you are pointing at null)
		Pointing DOWN THE STACK with a pointer is fine


3. User's Data and Working Storage
	a. Program's Static Data
		- global variables, also static variables
	b. Freestore (heap) --> Primary Focus of Lecture
		- operates by storing variables created dynamically, "new", "malloc", "calloc"
		- generally referenced by pointers
		- rule of thumb, if it has a pointer, probably on heap (cases where it might not be on heap though)
	c. Program Stack --> Primary Focus of Lecture
		- every time program is started, gets added to stack
		- when it completes, it gets removed from stack
		- will grow as you add functions


For C and C++ Memory Model:
	- typically compile the source code into the local OS / hardware-specific language



	Introducing ADTs

	- an abstract data type (ADT) is a mathematical structure that has a well defined and widely recognizable behaviour
	  - it contains data that may be accessed only in a prescribed manner, by a set of named operations
	  - Each operation has
	    - A signature, which describes the params and the type of the returned value
	    - a pre condition (logic statement) that specifies what is assumed to be true before the operation may be applied
	    - a post condition (logic statement) that described the value or affect of the operation

		ADTs are ABSTRACT

		- There may be multiple ways to implement a given ADT, but the abstract meaning is the same regardless
		  - that is, the abstract specification of, example, a stack, is the same
		  - this point is important!!!!
		- often, a programming language will supply means to create a heard interface around the ADT
		  - an interface enforces a limited access of clients to the internal details, which the clients should not be touching directly
		  - ex: interface of Stack supports push and pop operations, but not at
		  - C++/Java/C# provide strong language-level support for building interfaces via class definitions

		  Reference Parameters:
		  	just put an & after the type, int& x, int &y, any changes I made to the variable will occur to the caller too
		  	changing the variable in the calling environment
		  	Acts like a pointer, but use it as though it were the variable itself

		  	These are the NORM in C++
		  	This is super USEFUL

3 Kinds of Variables you Meet in C++
	- Global Variables:
	- are defined outside of any enclosing function/class/struct
	- usage is frowned upon
	- Local Variables
	- are defined within a function/method body
	- they come into existence when the function is classed, and die when the function terminates

	-Member/Instance Variables, are a sup part of a larger variables that is an instance of a struct or class
	- created when instance is created, die when instance dies

		Scopes of Identifiers and ARs:

		- in C,C++, an identifier (a name you picked, for a variable or procedure) is visible from its declaration until the end of the CURRENT SCOPE, which could be the:
		- end of current {} block
		- end of a procedure body
		- end of loop/if/switch
		- global variables are visible globally



Linked List Implementation:
- enter (constant)
- leave (constant)
- first (constant)
- nuke (Linear time)

2. Dynamic Arrays (C++ only)
	- storage is allocated on the heap
	- array bound can be a run time positive integer value
	- MUST delete when done (need "[]")

int * A = new int[N];

Binary Tree (more later)
	- special "root" Node
	- each node has two "child" links, a left and a right_tower
	- can be sorted (BST, heap) or not
	- non binary trees also exist
	- everything down left is less than me, everything down right is greater than me
	struct BST_Node{
		string val;
		Node * left;
		Node * right;
	}

	Priority Queue
	- like a queue, but each element has a value AND an integer priority (usually >= 0)
	- enter is the same as before
	- leave means remove the oldest element from among those with the MOST IMPORTANT priority
	- thus, the PQ is a data container that is both sorted (queues are sorted by priority) and ordered // TO DO

	- Always have to specify, does highest or lowest priority mean "most important"
	- for now, we will assume lowest is most important

	- note that the c++ standard library provides an implementation of priority_queue, which is what you should use in real life


	We usually implement a binary heap using a vector (element type is usually some kind of struct with a key)
	- do not explicitly store pointers, instead calculate index of a nodes parent/children; for node at index i of the underlying vector
		- left child is at index 2*i + 1
		- right child is at 2*i + 2

- the BST PROPERTY holds for every node in the tree:
- the keys of all nodes in the left subtree (if any) are < my key
- the keys of all nodes in the right subtree (if any) are > my key

TODO: read up on HEAP
TODO: finish notes from last class
