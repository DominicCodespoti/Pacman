package viewtests;

import model.Directions;
import view.IGameInput;

public class StubInput implements IGameInput {

  @Override
  public Directions getUserInput(Directions oldDirection) {
    return oldDirection;
  }

}
