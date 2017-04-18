#include <iostream>
#include <string>
#include <vector>
using namespace std;

class Vehicle{
public:
	virtual ~ Vehicle();//virtual destructor, NEEDs a definitions in Vehicle
	virtual void toString() const = 0;//pure virtual method, needs to be defined in child classes

protected:
	Vehicle();
	Vehicle(int num, string plate, string owner, string type, string brand);
	string getPlate() const;
	string getName() const;
	string getType() const;
	string getBrand() const;

private:
	int numWheels;
	string licensePlate;
	string ownerName;
	string vehicleType;
	string vehicleBrand;
};

Vehicle::~Vehicle() {}

Vehicle::Vehicle() {}

Vehicle::Vehicle(int num, string plate, string owner, string type, string brand) : numWheels(num), licensePlate(plate), ownerName(owner), vehicleType(type), vehicleBrand(brand) {}

string Vehicle::getPlate() const{
	return this->licensePlate;
}

string Vehicle::getName() const{
	return this->ownerName;
}

string Vehicle::getType() const{
	return this->vehicleType;
}

string Vehicle::getBrand() const{
	return this->vehicleBrand;
}
//=============================================================================

class Car: public Vehicle{
public:
	Car();
	Car(string plate, string owner, string type, string brand);
	virtual ~ Car();
	virtual void toString() const;
};

Car::~Car() {}
Car::Car() {}
Car::Car(string plate, string owner, string type, string brand) : Vehicle(4, plate, owner, type, brand) {}
void Car::toString() const {
	cout << "I am a car " << endl << "Num Wheels: 4 " << endl << "Plate: " << getPlate() << endl << "Owner Name: " << getName() << endl << "Type: " << getType() << endl << "Brand: " << getBrand() << endl;
	cout << endl;
}


//=============================================================================

class Motorcycle: public Vehicle{
public:
	Motorcycle();
	Motorcycle(string plate, string owner, string type, string brand);
	virtual ~ Motorcycle();
	virtual void toString() const;
};

Motorcycle::~Motorcycle() {}
Motorcycle::Motorcycle() {}
Motorcycle::Motorcycle(string plate, string owner, string type, string brand) : Vehicle(2, plate, owner, type, brand) {}
void Motorcycle::toString() const {
	cout << "I am a Motorcycle " << endl << "Num Wheels: 2 " << endl << "Plate: " << getPlate() << endl << "Owner Name: " << getName() << endl << "Type: " << getType() << endl << "Brand: " << getBrand() << endl;
	cout << endl;
}


int main (int argc, char * argv[]){
	Car * c1 = new Car("a", "a", "a", "a");
	Motorcycle * m1 = new Motorcycle("b", "b", "b", "b");
	c1->toString();
	m1->toString();

	Car c2 ("c", "c", "c", "c");
	Motorcycle m2 ("d", "d", "d", "d");
	c2.toString();
	m2.toString();

	vector <Vehicle * > vehicleList;
	Vehicle * c3, * c4,  * m3, * m4;
	c3 = new Car ("e", "e", "e", "e");
	c4 = new Car ("f", "f", "f", "f");
	m3 = new Motorcycle ("g", "g", "g", "g");
	m4 = new Motorcycle ("h", "h", "h", "h");

	vehicleList.push_back(c1);
	vehicleList.push_back(m1);
	vehicleList.push_back(c3);
	vehicleList.push_back(m3);
	vehicleList.push_back(c4);
	vehicleList.push_back(m4);
	cout << "printing from the vector of vehicle pointers" << endl;
	for (int i = 0; i < vehicleList.size(); i++){
		vehicleList.at(i)->toString();
	}


	delete c1;
	delete m1;

}
