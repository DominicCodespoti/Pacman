package GameLogicTests;

public interface IEntityObject extends IGameObject {

  void increaseScore();

  int getCurrentScore();

  boolean holdingDot();

  void updateCurrentDirection(Directions newDirection);

  Directions getCurrentDirection();

  String getName();

  boolean winCondition(int scoreCondition);

  void pickUpDot();
}
