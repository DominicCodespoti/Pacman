package Model;

public class Point {

  private final int x;
  private final int y;

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  @Override
  public boolean equals(Object otherPoint){
    final Point other = (Point) otherPoint;
    return (this.x == other.x) && (this.y == other.y);
  }
}
