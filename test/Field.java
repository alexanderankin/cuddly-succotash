package test;

// import src.Field;

public class Field {
  public static void main(String[] args) {

    src.Field f = new src.Field();
    // f.sayHW();

    f.setSize(10, 10);

    System.out.println("Resetting");
    f.reset();
    f.print();

    System.out.println("putBombs");
    f.putBombs(3);
    f.print();

    System.out.println("lostScreen");
    f.printDisplay();

    System.out.println("probing 1x1");
    f.probe(1, 1);
    f.print();
    f.printDisplay();
  }
}
