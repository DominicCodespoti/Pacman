package view;

import world.Direction;

public class StubInput implements IGameInput {

  @Override
  public Direction getUserInput(Direction oldDirection) {
    return oldDirection;
  }

}
