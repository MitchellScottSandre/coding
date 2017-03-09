#include <string>
using namespace std;

class Figure{
public:
	virtual ~ Figure();
	virtual double area() const = 0; // = 0 means no definition here
	virtual void draw() const = 0;// no definition here because of = 0
	void setPos(int x, int y);
	void getPos(int & x, int & y) const;
	void setColour(string colour);
	string getColour() const;
protected://----> we want children to be able to see the constructor
	Figure();
	Figure(string colour, int x, int y);
	string colour;
	int x, y;
};

Figure::Figure : colour("black")
//TODO


//CIRCLE
class Circle : public Figure {
public:
	Circle();
	Circle(string colour, int x, int y, int radius);
	virtual ~ Circle();
	virtual void draw() const;	//once virtual, always virtual. will Override here
	virtual double area () const;	//overriden
	void setRadius(int radius);
	void getRadius
	//TODO
};
Circle::Circle(string colour, int x, int y, int radius) : Figure(colour, x, y), radius(radius) {} //parent call first


//Rectangle
//TODO


Figure * f = ...
f->draw();//virtual
	-->virtual===> ask for dynamic type
	-->goes and looks up a draw() function in that dynamic type class
f->setPos();//not virtual
	-->Figure::setPos
