package view;

import world.Direction;

public interface IGameInput {

  // This shouldn't be responsible for remembering the previous input.
  // It would have to return something other than Direction. Maybe just a nullable Direction?
  Direction getUserInput(Direction oldInput);
}
