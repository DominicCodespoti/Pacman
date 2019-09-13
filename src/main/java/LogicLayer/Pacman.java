package LogicLayer;

public class Pacman implements IEntityObject {

  private Directions currentDirection;
  private String name;

  public Pacman(String name) {
    currentDirection = Directions.Up;
    this.name = name;
  }

  @Override
  public boolean holdingDot() {
    return false;
  }

  @Override
  public boolean isSolid() {
    return true;
  }

  @Override
  public boolean isEdible() {
    return true;
  }

  @Override
  public void effectWhenEaten() {
  }

  @Override
  public void updateCurrentDirection(Directions newDirection) {
    this.currentDirection = newDirection;
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
