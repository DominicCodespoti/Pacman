package Model;

public enum Directions {
  Down, Up, Left, Right;

  static {
    Up.oppositeDirection = Down;
    Down.oppositeDirection = Up;
    Left.oppositeDirection = Right;
    Right.oppositeDirection = Left;
  }

  private Directions oppositeDirection;

  public Directions getOppositeDirection() {
    return oppositeDirection;
  }
}
