public class Child {
    private String name;
    private Balloon pBalloon;
        public Child (String name, String bColour) {
        this.name = name;
        this.pBalloon = new Balloon (bColour);
    }
    public void speak () {
        System.out.print (name);
        if (pBalloon != null) {
            System.out.print (" with a ");
            pBalloon.speak();
        } else {
            System.out.println ("");
        }
    }
    // Simple unit test for Child class
    public static void main (String[] args) {
        Child trev = new Child ("Trevor", "red");
        trev.speak();
    }
}
