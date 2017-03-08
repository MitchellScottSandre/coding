class Balloon {//declaration
	public:
		Balloon();
		Balloon(string colour);
		virtual ~ Balloon();
		void speak() const;
		static int getNumBalloons() ; // static method decl
	private:
		const string colour;
		static int curNumBalloons; // static var declaration
};

int Balloon::curNumBalloons = 0; // static var definition

Balloon::Balloon () : colour ("grey") {
    cerr << colour << " balloon is born\n";
	Balloon::curNumBalloons++; // "Balloon::" helpfull but not necessary
}

Balloon::Balloon (string colour) : colour (colour) {
    cerr << colour << " balloon is born\n";
	curNumBalloons++;
}

Balloon::~Balloon() {
    cerr << colour << " balloon dies\n";
	curNumBalloons--;
}

void Balloon::speak () const {
    cout << colour << " balloon" << endl;
}

// Static method definition
int Balloon::getNumBalloons () {
    return curNumBalloons;
}

// Draw this
int main (int argc, char* argv[]) {

	// destructor called for others
	Balloon b1;              // default constructor
	Balloon b2 ("red");
	Balloon *b3;
	b1.speak();
	b2.speak();
	b3 = new Balloon ("green");
	b3->speak();
	Balloon b4 = (*b3);
	delete b3;
	b4.speak();
	/////
	Balloon rb ("red");
    Balloon rbc1 = rb; // output of num balloons here is 1!

}

class Child {
    public :
        Child (string name);
        Child (string name, string bColour);
        virtual ~Child();
        void speak() const;
        Balloon* pBalloon; // public field is a bad idea
    private :
        const string name;
};
// Child with no balloon
Child::Child(string name) : name(name), pBalloon(nullptr) {} // Child with a balloon
Child::Child(string name, string bColour): name(name), pBalloon(new Balloon (bColour)) {}

Child::~Child() {
    // It's actually safe to delete a nullptr
    if (nullptr != pBalloon) {
        delete pBalloon;
    }
}
void Child::speak () const {
    cout << name;
    if (nullptr != pBalloon) {
        cout << " with a ";
        pBalloon->speak();  // endl included!
    } else {
        cout << endl;
	}
}

// Let's try this!
int main (int argc, char* argv[]) {
    Child trev ("Trevor", "red");
    Child* ian = new Child ("Ian", "yellow");
    trev.speak();
    ian->speak();
    Child jdoe;
    jdoe.speak();
    // The below is legal if pBalloon is declared as public
    ian->pBalloon = trev.pBalloon;
    delete ian;
    trev.speak();  // Oh no, Ian took my balloon then left!
}
