package Model.EntityObjects;

import Model.Directions;
import Model.GameObjects.IGameObject;

public class Pacman implements IEntityObject, IGameObject {

  private final String name;
  private Directions currentDirection;
  private int score = 0;
  private boolean holdingDot = false;
  private boolean isMouthOpen = true;

  public Pacman(String name) {
    currentDirection = Directions.Up;
    this.name = name;
  }

  public void setIsMouthOpenToOpposite() {
    this.isMouthOpen = !this.isMouthOpen;
  }

  @Override
  public boolean isSolid() {
    return false;
  }

  @Override
  public boolean isEdible() {
    return true;
  }

  @Override
  public String getString() {
    switch (currentDirection) {
      case Up:
        return isMouthOpen ? "V" : "|";
      case Left:
        return isMouthOpen ? ">" : "-";
      case Right:
        return isMouthOpen ? "<" : "-";
      case Down:
        return isMouthOpen ? "^" : "|";
    }
    return "";
  }

  public void increaseScore() {
    score++;
  }

  public boolean isHoldingDot() {
    return holdingDot;
  }

  public void setHoldingDot(boolean isHolding) {
    holdingDot = isHolding;
  }

  @Override
  public int getCurrentScore() {
    return score;
  }

  public void updateCurrentDirection(Directions newDirection) {
    currentDirection = newDirection;
  }

  @Override
  public Directions getCurrentDirection() {
    return currentDirection;
  }

  @Override
  public String getName() {
    return name;
  }
}
