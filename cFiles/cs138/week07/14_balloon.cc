#include <iostream>
#include <string>
using namespace std;

//CLASS DECLARATION usually in .h file
class Balloon {
	public:
		Balloon(); 				//constructor
		Balloon(string colour); // constructor
		virtual ~ Balloon();		//destructor ---> the ~ means it is a destructor
		void speak() const;     // method

	private:
		string colour;
};

Balloon::Balloon(){//default constructor
	this->colour = "clear";
}

Balloon::Balloon(string colour){//constructor
	this->colour = colour;
}

Balloon::~Balloon(){
	cerr << colour << " Balloon dies" << endl;
}

void Balloon::speak() const {
	cout << "I'm a " << colour << " Balloon" << endl;
}

int main(int argc, char * argv[]){
	Balloon rb("red");
	rb.speak();
	Balloon cb;
	cb.speak();

	Balloon * pb = new Balloon("green");
	pb->speak();
	Balloon * pb2 = pb;
	pb2 -> speak();
	//pb2->colour = "blue"; // ERROR --> colour is PRIVATE
	delete pb;
	//delete pb2; // ERROR --> pb2 points to pb --> can't delete something that has been deleted
	return 0;
}
