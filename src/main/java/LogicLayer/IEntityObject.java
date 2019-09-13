package LogicLayer;

public interface IEntityObject extends IGameObject {

  boolean holdingDot();

  void updateCurrentDirection(Directions newDirection);

  Directions getCurrentDirection();

  String getName();
}
