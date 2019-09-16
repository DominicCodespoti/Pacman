package GameLogicTests;

public interface IEntityObject extends IGameObject {

  void increaseScore();

  boolean isHoldingDot();

  void updateCurrentDirection(Directions newDirection);

  Directions getCurrentDirection();

  String getName();

  boolean winCondition(int scoreCondition);

  void setHoldingDot(boolean isHolding);
}
