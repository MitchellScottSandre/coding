using namespace std;
#include <iostream>
//you can add other arguments when you run an object file by doing
//./argstuff one two three four yay fun
int main(int argc, char* argv[]){
	cout << "argc is " << argc << endl;
	for (int i = 0; i < argc; i++){
		cout << "argv[" << i << "] = " << argv[i] << endl;
	}
	return 0;
}
