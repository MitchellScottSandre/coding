#include <iostream>
#include <string>
#include <vector>
using namespace std;

class Animal{
	public:
        virtual ~Animal ();
        virtual void speak () const = 0; // A "pure virtual" method
    protected:
        Animal (string name);
        string getName () const;
    private:
        string name;

};

class Sheep: public Animal{
	public:
		Sheep(string sheepName);
	//inherit from Animal
	//define a single constructor of one stringargument
	//provide an appropriate implementation of speak()
	//need to define a virtual destructor
};

class Dog: public Animal{
	public:
		Dog(string dogName);
		
};

class Flock{//single dog, 0 or more sheep
	public:
        Flock (string dogName);
        virtual ~Flock ();
        void addSheep (string name);
        void soundOff ();//dog speaks first, iterate through vector of sheep
    private:
        Dog *dog;//pointer to a dog
        vector<Sheep*> sheepList;//vector of Sheep pointers
};
