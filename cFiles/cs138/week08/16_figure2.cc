#include <string>
using namespace std;

class Figure{
	public:
		virtual ~ Figure();
		//virtual double area() const = 0; // = 0 means no definition here
	    void draw() const = 0;// no definition here because of = 0,
		//draw just outputs words, doesn't draw figure to screen
		void setPos(int x, int y);
		void getPos(int & x, int & y) const;
		void setColour(string colour);
		string getColour() const;
	protected:
		Figure(string colour, int x, int y);
	private://----> we want children to be able to see the constructor
		virtual string getKind() const = 0;//will be declared later
			//children can't touch this, but they can OVERRIDE it / redefine it
		string colour;
		int x, y;
};

//no longer pure virtual (or even virtual at all)
void Figure::draw() const {
	cout << colour << " " << getKind() << endl;
}

//Circle...

string Circl::getKind() const {
	return "Circle";
}

/
