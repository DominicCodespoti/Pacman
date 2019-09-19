package Model;

import DataStructures.Directions;

public interface IEntityObject extends IGameObject {

  void increaseScore();

  boolean isHoldingDot();

  void setHoldingDot(boolean isHolding);

  int getCurrentScore();

  void updateCurrentDirection(Directions newDirection);

  Directions getCurrentDirection();

  String getName();
}
