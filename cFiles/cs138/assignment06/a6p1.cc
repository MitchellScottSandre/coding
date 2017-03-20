#include <iostream>
#include <string>
#include <vector>
#include <cctype>
#include <iomanip>
#include <fstream>
#include <iostream>
using namespace std;
int scrabbleValue(string word);
int scrabbleValue(char letter);

int scrabbleValue(string word){
	int sum = 0;
	for (int i = 0; i < word.size(); i++){
		sum += scrabbleValue(word[i]);
	}
	return sum;
}

int scrabbleValue(char letter){
	char c = letter;
	tolower(c);
	if (c =='q' || c=='z'){
		return 10;
	} else if (c =='j' || c == 'x'){
		return 8;
	} else if (c == 'k'){
		return 5;
	} else if (c == 'f' || c == 'h' || c == 'v' || c == 'w' || c == 'y'){
		return 4;
	} else if (c == 'b' || c == 'c' || c == 'm' || c == 'p'){
		return 3;
	} else if (c == 'd' || c == 'g'){
		return 2;
	} else if (c == 'e' || c == 'a' || c == 'i' || c == 'o' || c == 'n' || c == 'r' || c == 't' || c == 'l' || c == 's' || c == 'u'){
		return 1;
	}
	return 0;
}
