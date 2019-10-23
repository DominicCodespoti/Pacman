package ViewTests;

import Model.Directions;
import View.IGameInput;

public class StubInput implements IGameInput {

  @Override
  public Directions getUserInput(Directions oldDirection) {
    return oldDirection;
  }

}
