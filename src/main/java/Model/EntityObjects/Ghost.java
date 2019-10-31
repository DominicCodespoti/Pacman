package Model.EntityObjects;

import Model.Directions;
import Model.GameObjects.IGameObject;

public class Ghost implements IEntityObject, IGameObject {

  private final String name;
  private Directions currentDirection;
  private int score = 0;
  private boolean holdingDot = false;

  public Ghost(String name) {
    currentDirection = Directions.Up;
    this.name = name;
  }

  @Override
  public boolean isSolid() {
    return true;
  }

  @Override
  public boolean isEdible() {
    return false;
  }

  @Override
  public String getString() {
    return "G";
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

  public void updateCurrentDirection(Directions newDirection) {
    currentDirection = newDirection;
  }

  @Override
  public int getCurrentScore() {
    return score;
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
