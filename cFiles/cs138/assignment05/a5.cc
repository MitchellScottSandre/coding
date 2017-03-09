#include <iostream>
#include <string>
#include <vector>
using namespace std;

class Animal{
	public:
        virtual ~Animal ();//needs a definition!!
        virtual void speak () const = 0; // A "pure virtual" method, no definition required
    protected:
        Animal (string name);
        string getName() const;
    private:
        string name;

};

Animal::~Animal(){}

Animal::Animal(string name) : name(name){}

string Animal::getName() const{
	return this->name;
}

//====================================END OF ANIMAL CLASS ================================

//====================================START OF SHEEP CLASS ================================
class Sheep: public Animal{
	public:
		Sheep(string sheepName);
	    virtual void speak () const;
		virtual ~ Sheep();
};

Sheep::Sheep(string sheepName) : Animal(sheepName) {
	//cerr << "Sheep " << getName() << " has been created\n";
} // constructor

void Sheep::speak() const {
	cout << "Sheep " << getName() << " says \"baaa\".\n";
}

Sheep::~Sheep(){
	//cerr << "Sheep " << getName() << " has been deleted\n";
}
//====================================END OF SHEEP CLASS ================================


//====================================START OF DOG CLASS ================================
class Dog: public Animal{
	public:
		Dog(string dogName);
		virtual void speak () const;
		virtual ~ Dog();
};

Dog::Dog(string dogName) : Animal(dogName) {
	//cerr << "Dog " << getName() << " has been created\n";
} //constructor

void Dog::speak() const {
	cout << "Dog " << getName() << " says \"woof\".\n";
}

Dog::~Dog(){
	//cerr << "Dog " << getName() << " has been deleted\n";
}

//====================================END OF DOG CLASS ================================

class Flock{//single dog, 0 or more sheep
	public:
        Flock (string dogName);
        virtual ~Flock ();
        void addSheep (string name);
        void soundOff ();//dog speaks first, iterate through vector of sheep
    private:
        Dog * dog;//pointer to a dog
        vector<Sheep*> sheepList;//vector of Sheep pointers
};


Flock::Flock(string dogName){
	dog = new Dog(dogName);
}

void Flock::addSheep(string name){
	Sheep * tempSheep = new Sheep(name);
	sheepList.push_back(tempSheep);
}

void Flock::soundOff(){
	cout <<"The flock of " << sheepList.size() << " sheep speaks!" << endl;
	cout << "    ";
	dog->speak();
	for (int i = 0 ; i < sheepList.size(); i++){
		cout << "    ";
		sheepList[i]->speak();
	}
	cout << endl;
}

Flock::~Flock(){//delete the flock
	delete dog;
	for (int i = 0; i < sheepList.size(); i++){
		delete(sheepList[i]);
	}
}
