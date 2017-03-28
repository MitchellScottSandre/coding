#ifndef __CHILD_H__
#define __CHILD_H__
#include <string>
#include "Balloon.h"
using namespace std;
class Child{
    public:
        Child (string name, string bColour);
        virtual ~Child();
        void speak() const;
    private:
        string name;
        Balloon* pb;
};
#endif /* __CHILD_H__ */
