package Model.EntityObjects;

import DataStructures.Directions;
import Model.IEntityObject;

public class Pacman implements IEntityObject {

  private Directions currentDirection;
  private String name;
  private int score = 0;
  private boolean holdingDot = false;

  public Pacman(String name) {
    currentDirection = Directions.Up;
    this.name = name;
  }

  @Override
  public String getString() {
    switch (currentDirection) {
      case Up:
        return "V";
      case Left:
        return ">";
      case Right:
        return "<";
      case Down:
        return "^";
    }
    return "";
  }

  @Override
  public boolean isHoldingDot() {
    return holdingDot;
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
  public void increaseScore() {
    score++;
  }

  @Override
  public boolean winCondition(int scoreCondition) {
    return score >= scoreCondition;
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

  @Override
  public void setHoldingDot(boolean isHolding) {
    holdingDot = isHolding;
  }
}
