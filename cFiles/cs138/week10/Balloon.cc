#include <iostream>
#include <string>
#include "Balloon.h"
using namespace std;

Balloon::Balloon(string colour) : colour(colour) {}

void Balloon::speak() const{
	cout << colour << " balloon" << endl;
}
