#include <string>
#include <iostream>
#include <map>
#include <set>
using namespace std;
// Example with user defined class and operator<
class Student {
    public:
        Student (string name, int sNum, double gpa);
        string getName() const;
        int getSNum() const;
        double getGPA() const;
    private:
        string name;
        int sNum;
        double gpa;
};
Student::Student(string name, int sNum, double gpa)
    : name(name), sNum(sNum), gpa(gpa) {}
string Student::getName() const {return name;}
int Student::getSNum() const {return sNum;}
double Student::getGPA() const {return gpa;}


// Extending operator< and operator << to work on Students.
// This is called operator overloading
// Need not to be friend of Student as getSNum() is public
bool operator< (const Student& s1, const Student& s2) {
    return s1.getSNum() < s2.getSNum();
}
// Also need not be a friend of Student
ostream& operator<< (ostream &os, const Student & s) {
    os << s.getName() << " " << s.getSNum() << " " << s.getGPA();
	return os;
}

int main (int argc, char* argv[]) {
    Student * pJohn = new Student ("John Smit", 666, 3.7);
    Student * pMary = new Student ("Mary Jones", 345, 3.4);
    map<string,Student*> m;
    m["johnS"]= pJohn;
    m["maryJ"]=pMary;
    // Will print alphabetical order by key (name)
    for (map<string,Student*>::const_iterator i=m.begin();
            i!=m.end(); i++) {
        cout << (*i).first << " " << (*i).second->getName()
            << " " << (*i).second->getGPA() << endl;
    }
    set<Student> s;
    s.insert(*pJohn);
    s.insert(*pMary);
    // Will print in numeric order of sNum
    for (set<Student>::iterator i=s.begin(); i!=s.end();i++){
        cout << (*i) << endl;
    }
}
