package Model.EntityObjects;

import DataStructures.Directions;
import Model.IEntityObject;

public class Pacman implements IEntityObject {

  private Directions currentDirection;
  private String name;
  private int score = 0;
  private boolean holdingDot = false;
  private boolean wakaWaka = true;

  public Pacman(String name) {
    currentDirection = Directions.Up;
    this.name = name;
  }

  public void setWakaWaka(boolean wakaWaka) {
    this.wakaWaka = wakaWaka;
  }

  @Override
  public String getString() {
    switch (currentDirection) {
      case Up:
        return wakaWaka ? "V" : "|";
      case Left:
        return wakaWaka ? ">" : "-";
      case Right:
        return wakaWaka ? "<" : "-";
      case Down:
        return wakaWaka ? "^" : "|";
    }
    return "";
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
