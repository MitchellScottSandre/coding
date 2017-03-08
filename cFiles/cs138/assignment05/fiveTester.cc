#include <iostream>
#include <vector>
#include <string>
#include "a5.cc"
using namespace std;

int main (int argc, char * argv[]){
	Flock *myFlock = new Flock ("Spot");
    myFlock->soundOff();
	myFlock->addSheep ("Daisy");
    myFlock->addSheep ("Clover");
    myFlock->addSheep ("Estelle");
    myFlock->soundOff();
    delete myFlock;
    myFlock = new Flock ("Rover");
    myFlock->addSheep ("Butch");
    myFlock->addSheep ("Jonno");
    myFlock->soundOff();
    delete myFlock;
	return 0;
}
