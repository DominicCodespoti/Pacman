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

  @Override
  public void increaseScore() {
    score++;
  }

  @Override
  public boolean isHoldingDot() {
    return holdingDot;
  }

  @Override
  public void setHoldingDot(boolean isHolding) {
    holdingDot = isHolding;
  }

  @Override
  public int getCurrentScore() {
    return score;
  }

  @Override
  public void updateCurrentDirection(Directions newDirection) {
    this.currentDirection = newDirection;
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
