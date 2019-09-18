package Model.GameObjects;

import Model.IGameObject;

public class Wall implements IGameObject {

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
    return "=";
  }
}