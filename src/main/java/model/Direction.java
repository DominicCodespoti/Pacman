package model;

public enum Direction {
  Down, Up, Left, Right;

  static {
    Up.oppositeDirection = Down;
    Down.oppositeDirection = Up;
    Left.oppositeDirection = Right;
    Right.oppositeDirection = Left;
  }

  private Direction oppositeDirection;

  public Direction getOppositeDirection() {
    return oppositeDirection;
  }
}
