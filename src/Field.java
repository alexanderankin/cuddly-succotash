package src;

import java.util.Random;

public class Field {
  private Cell[][] cells;

  public Field () {
    System.out.println("made a new object");
    cells = new Cell[0][0];
  }

  public void reset() {
    if (cells != null)
    
    for (int i = 0; i < cells.length; i++) {
      Cell[] row = cells[i];
      for (int j = 0; j < row.length; j++) {
        if (row[j] == null) {
          row[j] = new Cell();
        } else {
          row[j].setState(CellState.CLEAR);
        }
      }
    }
  }

  public void setSize(int rows, int cols) {
    boolean badlength = cells.length != rows;

    boolean heightexists = cells.length > 0;
    boolean badheight = true;
    if (heightexists && cells[0].length == cols)
      badheight = false;

    if (badheight || badlength) {
      cells = new Cell[rows][cols];
    }
  }

  public void print() {
    if (cells != null) {
      for (Cell[] row : cells) {
        System.out.println();

        for (Cell cell : row) {
          if (cell != null) {
            System.out.print(cell.toString() + ",");
          } else {
            System.out.print("nullpe ");
          }
        }

      }
      
      System.out.println();
    }
  }

  public void printDisplay() {
    if (cells != null) {
      for (Cell[] row : cells) {
        System.out.println();

        for (Cell cell : row) {
          if (cell != null) {
            System.out.print(cell.toStringShowScreen() + ",");
          } else {
            System.out.print("nullpe ");
          }
        }

      }
      
      System.out.println();
    }
  }

  public void putBombs(int count) {
    if (cells == null) return;
    Random rn = new Random();
    for (int i = 0; i < count; i++) {
      this.cells[ rn.nextInt(cells.length) ]
                [ rn.nextInt(cells[0].length) ]
        .setState(CellState.MINE);
    }

    int cellsCount = 0;
    // System.out.println(cellsCount);
    int cellWeight;
    for (int i = 1; i < cells.length - 1; i++) {
      Cell[] row = cells[i];
      for (int j = 1; j < row.length - 1; j++) {
        cellsCount++;  // cells[i][j];
        cellWeight = 0;
        if (cells[i - 1][j - 1].isBomb()) cellWeight++;
        if (cells[i - 1][j - 0].isBomb()) cellWeight++;
        if (cells[i - 1][j + 1].isBomb()) cellWeight++;
        if (cells[i - 0][j - 1].isBomb()) cellWeight++;
        /*if (cells[i - 0][j - 0].isBomb()) cellWeight++;*/ // lol
        if (cells[i - 0][j + 1].isBomb()) cellWeight++;
        if (cells[i + 1][j - 1].isBomb()) cellWeight++;
        if (cells[i + 1][j - 0].isBomb()) cellWeight++;
        if (cells[i + 1][j + 1].isBomb()) cellWeight++;
        cells[i][j].setNumber(cellWeight);
      }
    }

    // first last incomplete rows
    for (int j = 1; j < cells[0].length - 1; j++) {
      cellsCount++;  // cells[0][j];
      cellsCount++;  // cells[cells.length - 1][j];
      
      cellWeight = 0;
      if (cells[1][j - 1].isBomb()) cellWeight++;
      if (cells[1][j - 0].isBomb()) cellWeight++;
      if (cells[1][j + 1].isBomb()) cellWeight++;
      if (cells[0][j - 1].isBomb()) cellWeight++;
      /*if (cells[0][j - 0].isBomb()) cellWeight++;*/ // lol
      if (cells[0][j + 1].isBomb()) cellWeight++;
      cells[0][j].setNumber(cellWeight);

      cellWeight = 0;
      if (cells[cells.length - 2][j - 1].isBomb()) cellWeight++;
      if (cells[cells.length - 2][j - 0].isBomb()) cellWeight++;
      if (cells[cells.length - 2][j + 1].isBomb()) cellWeight++;
      if (cells[cells.length - 1][j - 1].isBomb()) cellWeight++;
      /*if (cells[cells.length - 1][j - 0].isBomb()) cellWeight++;*/ // lol
      if (cells[cells.length - 1][j + 1].isBomb()) cellWeight++;
      cells[cells.length - 1][j - 0].setNumber(cellWeight);
    }

    // first last incomplete cols
    for (int i = 1; i < cells.length - 1; i++) {
      cellsCount++;  // cells[i][0];
      cellsCount++;  // cells[i][cells[0].length];

      cellWeight = 0;
      if (cells[i - 1][1].isBomb()) cellWeight++;
      if (cells[i - 0][1].isBomb()) cellWeight++;
      if (cells[i + 1][1].isBomb()) cellWeight++;
      if (cells[i - 1][0].isBomb()) cellWeight++;
      /*if (cells[i - 0][0].isBomb()) cellWeight++;*/ // lol
      if (cells[i - 1][0].isBomb()) cellWeight++;
      cells[i][0].setNumber(cellWeight);

      cellWeight = 0;
      if (cells[i - 1][cells[0].length - 2].isBomb()) cellWeight++;
      if (cells[i - 0][cells[0].length - 2].isBomb()) cellWeight++;
      if (cells[i + 1][cells[0].length - 2].isBomb()) cellWeight++;
      if (cells[i - 1][cells[0].length - 1].isBomb()) cellWeight++;
      /*if (cells[i - 0][cells[0].length - 1].isBomb()) cellWeight++;*/ // lol
      if (cells[i + 1][cells[0].length - 1].isBomb()) cellWeight++;
      cells[i][cells[0].length - 1].setNumber(cellWeight);
    }
    // System.out.println(cellsCount);

    // top corner
    cellWeight = 0;
    if (cells[0][1].isBomb()) cellWeight++;
    if (cells[1][0].isBomb()) cellWeight++;
    if (cells[1][1].isBomb()) cellWeight++;
    cells[0][0].setNumber(cellWeight);

    // bottom corner
    cellWeight = 0;
    if (cells[cells.length - 2][cells[0].length - 2].isBomb()) cellWeight++;
    if (cells[cells.length - 2][cells[0].length - 1].isBomb()) cellWeight++;
    if (cells[cells.length - 1][cells[0].length - 2].isBomb()) cellWeight++;
    cells[cells.length - 1][cells[0].length - 1].setNumber(cellWeight);

    cellWeight = 0;
    if (cells[0][cells[0].length - 2].isBomb()) cellWeight++;
    if (cells[1][cells[0].length - 1].isBomb()) cellWeight++;
    if (cells[1][cells[0].length - 1].isBomb()) cellWeight++;
    cells[0][cells[0].length - 1].setNumber(cellWeight);

    cellWeight = 0;
    if (cells[cells.length - 2][0].isBomb()) cellWeight++;
    if (cells[cells.length - 2][1].isBomb()) cellWeight++;
    if (cells[cells.length - 1][1].isBomb()) cellWeight++;
    cells[cells.length - 1][0].setNumber(cellWeight);
  }

  public boolean probe(int x, int y) {
    if (cells == null) return false;
    if (x > cells.length - 1 || y > cells[0].length - 1) return false;

    Cell chosen = cells[x][y];
    if (chosen.isBomb()) {
      chosen.setState(CellState.BLOWN);
      return true;
    }

    _probe(x, y);

    return false;
  }

  /** this ones recursive */
  private void _probe(int x, int y) {
    System.out.printf("Probing cells: %4d, %4d.\n", x, y);
    cells[x][y].tryProbe();

    // need to go on and check adjacent
    if (cells[x][y].getNumber() != 0) {
      cells[x][y].setState(CellState.NUMBERED);
    }

    // ok, done after this one.
    else {
      cells[x][y].setState(CellState.CLEAR);
    }
  }
}

enum CellState {
  CLEAR("   "), FLAG("---"), MINE("___"), BLOWN("###"), NUMBERED("-#-");

  private String representation;

  CellState(String r) {
    representation = r;
  }

  public String getR() {
    return this.representation;
  }
}

class Cell {
  private CellState state;
  private int weight;
  private boolean probed;

  public Cell() {
    this.state = CellState.CLEAR;
    this.weight = 0;
    this.probed = false;
  }

  public void setState(CellState s) {
    this.state = s;
  }

  public CellState getState() {
    return this.state;
  }

  /**
   * This method prints the most information
   */
  @Override
  public String toString() {
    if (this.state == CellState.NUMBERED) {
      return String.format("%3d", this.getNumber());
      // return " " + this.getNumber() + " ";
    }
    return this.state.getR();
  }

  /**
   * This method shows what would go on a screen
   * 
   * if not probed, show [ ]
   */
  public String toStringShowScreen() {
    if (this.probed) {
      if (this.state == CellState.NUMBERED)
        return String.format("%3d", this.getNumber());
      return this.state.getR();
    }
    
    return "[ ]";
  }

  public boolean isBomb() {
    return this.state == CellState.MINE;
  }

  public void setNumber(int n) {
    if (n != 0) this.state = CellState.NUMBERED;
    this.weight = n;
  }

  public int getNumber() {
    return this.weight;
  }

  public void tryProbe() {
    this.probed = true;
    return;
  }
}