class Rectangle {
	public:
		Rectangle();
		Rectangle(int w, int h);
		virtual ~ Rectangle();
		virtual void Draw();
		virtual void setSize(int w, int h);
	private:
		int w, h;
};

void Rectangle::setSize(int w, int h){
	this->w=w;
	this->h=h;
}

class Square{
	public:
		Sqaure();
		Square(int w);
		virtual ~ Sqaure();
		virtual void Draw();
		virtual void setSize(int w);
	private:
		int w;
};

void Square:setSize(int w){ // can only have INITIALIZERS with CONSTRUCTORS (the ... : h(h) ... thing)
	this->w=w;
}

void Square::setSize(int w){
	this->w = w;
}
