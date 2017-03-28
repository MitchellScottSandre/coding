#include <iostream>
#include <string>
#include "Balloon.h"
#include "Child.h"
using namespace std;

int main (int argc, char * argv[]) {
    Child ian ("Ian", "red");
    Child trev ("Trevor", "green");
    Child * ems = new Child("Emily", "yellow");
    trev.speak();
    ian.speak();
    ems->speak();
}
