#include <iostream>
using namespace std;
int main (int argc, char* argv[]) {
	double sum = 0;
	int count = 0;
	while (true) {
		double next;
		cin >> next;
		if (cin.fail()) {
			break;
		}
		sum += next;
		count++;
	}
	// awkward but correct version
	// or  if (!cin) {  // break out of loop
	if (count > 0) {
		cout << "Avg is "  << sum/count << endl;
	}
	return 0;
}
