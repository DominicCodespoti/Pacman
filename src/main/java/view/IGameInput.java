package view;

import world.Direction;

public interface IGameInput {

  Direction getUserInput(Direction oldInput);
}
