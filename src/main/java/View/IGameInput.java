package View;

import Model.Directions;

public interface IGameInput {

  Directions getUserInput(Directions oldInput);
}
