package view;

import model.Directions;

public interface IGameInput {

  Directions getUserInput(Directions oldInput);
}
