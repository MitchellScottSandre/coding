#include <iostream>
using namespace std;

int main(int argc, char * argv[]){
	cout << "part 1" << endl;
	for (int i = 0; i < 10000; i++){
		if((1176*i + 1)% 125 == 0  ){
			cout << i << endl;

		}
	}


	return 0;
}
