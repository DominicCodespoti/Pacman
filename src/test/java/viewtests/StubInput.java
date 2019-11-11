package viewtests;

import model.Direction;
import view.IGameInput;

public class StubInput implements IGameInput {

  @Override
  public Direction getUserInput(Direction oldDirection) {
    return oldDirection;
  }

}
