public class Balloon{
	private String colour;

	public Balloon(String colour){
		this.colour = colour;
	}

	public void speak(){
		System.out.println(colour + " balloon!");
	}

	public static void main (String[] args){
		Balloon rb = new Balloon("red");
		rb.speak();
		Balloon gb = new Balloon("green");
		gb.speak();
	}
}
