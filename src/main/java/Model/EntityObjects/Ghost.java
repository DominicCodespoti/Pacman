package Model.EntityObjects;

import DataStructures.Directions;
import Model.IEntityObject;

public class Ghost implements IEntityObject {

  private Directions currentDirection;
  private String name;
  private int score = 0;
  private boolean holdingDot = false;

  public Ghost(String name) {
    currentDirection = Directions.Up;
    this.name = name;
  }

  @Override
  public String getString() {
    return "G";
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
  public boolean isSolid() {
    return false;
  }

  @Override
  public boolean isEdible() {
    return false;
  }

  @Override
  public void increaseScore() {
    score++;
  }

  @Override
  public boolean winCondition(int scoreCondition) {
    return score == scoreCondition;
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
