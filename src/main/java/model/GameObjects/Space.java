package model.GameObjects;

public class Space implements IGameObject {

  @Override
  public boolean isSolid() {
    return false;
  }

  @Override
  public boolean isEdible() {
    return false;
  }

  @Override
  public String getString() {
    return " ";
  }
}
