package GameLogicTests;

public class Space implements IGameObject {

  @Override
  public boolean isSolid() {
    return false;
  }

  @Override
  public boolean isEdible() {
    return false;
  }
}
