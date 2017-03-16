//this design, where we DO NOT inherit vector, is much beter
class Stack : {
	public:
		Stack();
		virtual ~ Stack();
		void push (string s);
		string pop();
	private:
		vector<string> v;
}

Stack::Stack() : v() {}
Stack::~Stack(){}
void Stack::push(string s){
	v.push_back(s);
}

string Stack::pop(){
	string s = v.back();
	v.pop_back();
	return s;
}
