#include <iostream>
#include <cassert>
#include <vector>
using namespace std;

const string BLOCK = "X";

void printBlock(int blockLength, int displaySize){
	int thisBlockDisplaySize = blockLength * 2 - 1;
	int lengthPadding = (displaySize - thisBlockDisplaySize) / 2;
	for (int i = 0; i < lengthPadding; i++){
		cout << " ";
	}
	for (int i = 0; i < blockLength; i++){
		if (i == blockLength - 1){
			cout << BLOCK;
		} else {
			cout << BLOCK << " ";
		}
	}
	for (int i = 0; i < lengthPadding; i++){
		cout << " ";
	}

}


void displayTowers(const vector <vector <int> > & allTowers, int displaySize, int originalHeight){
	int max = originalHeight;//maxHeight(allTowers);

	for (int i = max - 1; i >= 0; i--){//print from top to bottom
		for (int tow = 0; tow < 3; tow++){//towers left to right
			if ((int) allTowers[tow].size() >=  i + 1){
				printBlock(allTowers[tow][i], displaySize);
			} else {//print empty
				for (int i = 0; i < displaySize; i++){
					cout << " ";
				}
			}
			cout << "  "; // space between towers
		}
		cout << endl;
	}
	cout << endl;
}


void hanoi(int N, int src, int dest, int temp, vector <vector <int> > & allTowers, int & displaySize, int & originalHeight){
	if (N > 0){
		hanoi(N - 1, src, temp, dest, allTowers, displaySize, originalHeight);
		cout << "Move from " << src << " to " << dest << endl;
		int srcVal = allTowers[src - 1].back();
		allTowers[src - 1].pop_back();
		allTowers[dest - 1].push_back(srcVal);
		displayTowers(allTowers, displaySize, originalHeight);
		hanoi(N - 1, temp, dest, src, allTowers, displaySize, originalHeight);//switched src with temp, temp with dest, dest with src
	}
}

void initializeFirstTower(vector <int> & s, int n){
	int width = n;
	for (int i = 0; i < n; i++){
		s.push_back(width);
		width--;
	}
}

int main(int argc, char * argv[]){
	int size, displaySize;
	cout << "Enter size of hanoi stack: ";
	cin >> size;
	assert(size > 0);
    displaySize = size * 2 - 1;
	vector <int> left_tower;
	vector <int> middle_tower;
	vector <int> right_tower;
	initializeFirstTower(left_tower, size);
	vector <vector <int> > towers;
	towers.push_back(left_tower);
	towers.push_back(middle_tower);
	towers.push_back(right_tower);
	hanoi(size, 1, 3, 2, towers, displaySize, size);
	return 0;
}
