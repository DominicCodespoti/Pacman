package view;

import model.Direction;

public interface IGameInput {

  Direction getUserInput(Direction oldInput);
}
