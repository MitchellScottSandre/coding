#ifndef __BALLOON_H__
#define __BALLOON_H__
#include <string>
using namespace std;

class Balloon{
public:
	Balloon(string colour);
	void speak() const;
private:
	string colour;
};

#endif // __BALLOON_H__
