#include <iostream>
#include <map>
using namespace std;
//std::map <key_type, data_type, [comparison_function]>
//erase(key_type key_value);
//empty() ; is empty or not
int main (int argc, char * argv[]){
	map <string, char> grade_list;
	grade_list["John"] = 'B';
	grade_list["John"] = 'A';
	cout<<"Before: The class is size "<<grade_list.size()<<endl;
	grade_list.erase("John");
	cout<<"After: The class is size "<<grade_list.size()<<endl;
	grade_list.clear();
	if(grade_list.find("Tim") == grade_list.end()){//find returns an iterator, which would be at the END if it could not find the search value
    	cout<<"Tim is not in the map!"<<endl;
	}
	return 0;
}
