package Model;

public class Dot implements IGameObject {

  @Override
  public boolean isSolid() {
    return false;
  }

  @Override
  public boolean isEdible() {
    return true;
  }

  @Override
  public String getString(){
    return ".";
  }
}
