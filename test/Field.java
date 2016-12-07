package test;

// import src.Field;

public class Field {
  public static void main(String[] args) {

    src.Field f = new src.Field();
    // f.sayHW();

    f.setSize(10, 10);

    System.out.println("Resetting (peek)");
    f.reset();
    f.print();

    System.out.println("putBombs (peek)");
    f.putBombs(3);
    f.print();

    System.out.println("lostScreen (display)");
    f.printDisplay();

    System.out.println("probing 1x1 (peek + display)");
    f.probe(0, 1);  f.probe(1, 1);
    f.probe(0, 2);  f.probe(1, 2);
    f.probe(0, 3);  f.probe(1, 3);
    f.probe(0, 4);  f.probe(1, 4);
    f.probe(0, 5);  f.probe(1, 5);
    f.probe(0, 6);
    f.probe(0, 7);
    f.probe(0, 8);
    f.print();
    f.printDisplay();
  }
}
