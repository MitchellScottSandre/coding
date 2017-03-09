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


Figure::Figure() : colour ("black"), x(0), y(0) {}
Figure::Figure(string colour, int x, int y) : colour(colour), x(x), y(y) {}
Figure::~Figure(){}
void Figure::setPos (int x, int y) {
	this->x = x;
	this->y = y;
}
void Figure::getPos (int &x, int &y) const {
    x = this->x;
y = this->y; }
void Figure::setColour (string colour) {
    this->colour = colour;
}
string Figure::getColour () const {
    return colour;
}
// No defs for pure virtual Figure::draw or Figure::area!


//CIRCLE
class Circle : public Figure {
	public:
		Circle();
		Circle(string colour, int x, int y, int radius);
		virtual ~ Circle();
		virtual void draw() const;	//once virtual, always virtual. will Override here
		virtual double area () const;	//overriden
		void setRadius(int radius);
		void getRadius(int & radius) const;
		static const double PI;
	private :
		int radius;
};
Circle::Circle(string colour, int x, int y, int radius) : Figure(colour, x, y), radius(radius) {} //parent call first

Circle::Circle() : Figure (), radius(0) {}

Circle::Circle(string colour, int x, int y, int radius): Figure (colour, x, y), radius(radius) {}

Circle::~Circle(){} //destructor

const double Circle::PI=3.14; //PIdayisreally22/7 double

Circle::area() const {
    return radius * radius * PI;
}
void Circle::draw() const {
	cout << "Circle " << colour << " "<< x << " " << y << endl;
}
void Circle::setRadius (int radius) {
    this->radius = radius;
}
void Circle::getRadius (int & radius) const {
    radius = this->radius;
}


//Rectangle
class Rectangle : public Figure {
	public:
		Rectangle();
		Rectangle(string colour, int x, int y, int w, int h);
		virtual ~ Rectangle();
		virtual void draw() const; //virtual since it overrides virtual draw from Figure2
		virtual double area() const;//overridden
		void setSize(int w, int h);
		void getSize(int & w, int & h);
	private:
		int w, h;
};

Rectangle::Rectangle() : Figure (), w(0), h(0) {}
Rectangle::Rectangle(string colour, int x, int y, int w, int h): Figure (colour, x, y), w(w), h(h) {} Rectangle::~Rectangle(){}

double Rectangle::area() const {
	return w * h;
}
void Rectangle::draw() const {//toString
    cout << "Rectangle " << colour << " " << x << " " << y << endl;
}
void Rectangle::setSize (int w, int h){
	this->w = w;
	this->h = h;
}
void Rectangle::getSize (int & w, int & h) const {
    w = this->w;
	h = this->h;
}

int main (int argc, char* argv[]) {
// Figure f; // Illegal
Circle *c1 = new Circle ("cyan", 0, 0, 5); Rectangle r1 ("red", 5, 10, 2, 3);
Circle c2 ("green", 1, 1, 3);
Figure* f = new Circle ("blue",2,5,6); //coin flip? vector<Figure*> v; // Polymorphic container! v.push_back(c1);
v.push_back(&r1); // Legal, but don't do this! v.push_back(&c2); // ditto
v.push_back (f);
for (int i=0; i<v.size(); i++) {
Figure* fp = v.at(i); fp->draw();
    }
    delete c1;
    delete f;
}

// Figure * f = ...
// f->draw();//virtual
// 	-->virtual===> ask for dynamic type
// 	-->goes and looks up a draw() function in that dynamic type class
// f->setPos();//not virtual
// 	-->Figure::setPos
