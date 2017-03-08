class Child {
	public :
		Child (string name);
		virtual ~Child();
		void speak() const;
		void receiveBalloon (Balloon* pBalloon); Balloon* giveAwayBalloon ();
    private :
        string name;
		Balloon* pBalloon;
};

void Child::receiveBalloon (Balloon* pBalloon) {
    // What if the Child has a Balloon already?
    // Easy answer: just delete it
    if (nullptr != this->pBalloon) {
       delete this->pBalloon;
    }
    this->pBalloon = pBalloon;
}

Balloon* Child::giveAwayBalloon() {
    // Is it reasonable to abort if I have no Balloon?
    // Hmmm ...
    assert (nullptr != pBalloon);
    Balloon* ans = pBalloon;
    pBalloon = nullptr;
    return ans;
}

/////////
//with accessors and mutators

class Child {
    public :
        Child (string name);
        virtual ~Child();
        void speak() const;
        void receiveBalloon (Balloon* pBalloon); // mutator
		Balloon* giveAwayBalloon ();//mutator
		string getName () const;	//accessor
		void setName (string name); //mutator
    private :
        string name;         // not a const in this world
        Balloon* pBalloon;
};
string Child::getName() const {
    return name;
}
void Child::setName(string name) {
    this->name = name;  // Could do some instrumentation here
}
