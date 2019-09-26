package Model.EntityObjects;

import Model.Directions;
import Model.GameObjects.IGameObject;

public interface IEntityObject extends IGameObject {

  void increaseScore();

  boolean isHoldingDot();

  void setHoldingDot(boolean isHolding);

  int getCurrentScore();

  void updateCurrentDirection(Directions newDirection);

  Directions getCurrentDirection();

  String getName();
}