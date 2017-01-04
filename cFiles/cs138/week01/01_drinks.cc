
using namespace std;
#include<iostream>
#include<string>
const string kidDrink = "juice";  // globals
string adultDrink = "coffee";
int main (int argc, char* argv[]) {
    int age = 100;
    while (age > 0) {
        cout << "How old are you? ";
        cin >> age;
        cout << "Would you like some ";
        if (age < 18) {
            cout << kidDrink << "?" << endl;
        } else {
            cout << adultDrink << "?" << endl;
        }
        if (age == 47) {
            adultDrink = "beer"; // change global var!
} }
return 0; }
