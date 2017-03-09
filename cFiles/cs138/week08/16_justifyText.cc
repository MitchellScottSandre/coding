class Justifier{
	public:
		virtual ~ Justifier();
		void justifyMyText();
	protected:
		Justifier (int MaxLineLength, ifstream & instream, ofstream & outstream);
	private:
		virtual string justifyLine(string line) = 0;
		//member variables
		const int MaxLineLength;
		iftsream & instream;
		ofstream & outstream;
};

void Justifier::justifyMyText () {
    while (!instream) {
        // ...
		// Read in tokens to build up curLine up to // the MaxLineLength
		outstream << justifyLine (curLine) << endl; // ...
	}
}

class SmoothJustifier : public Justifier {
    public:
        SmoothJustifier (int MaxLineLength,
                ifstream &instream, ofstream &outstream);
        virtual ~SmoothJustifier ();
    private:
		virtual string justifyLine (string line);
};
// ...
string SmoothJustifier::justifyLine (string line) {
    string justifiedLine = line;
    // do smooth justification stuff
    return justifiedLine;
}
string RaggedRightJustifier::justifyLine (string line) {
    // No work needs to be done!
    return line;
}
