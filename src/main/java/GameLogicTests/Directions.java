package GameLogicTests;

public enum Directions {
  Down,
  Up,
  Left,
  Right;

  private Directions oppositeDirection;

  static {
    Up.oppositeDirection = Down;
    Down.oppositeDirection = Up;
    Left.oppositeDirection = Right;
    Right.oppositeDirection = Left;
  }

  public Directions getOppositeDirection(){
    return oppositeDirection;
  }
}
