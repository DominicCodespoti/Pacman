package View;

import Model.Directions;

public interface IGameInput {

  int getUserInput();

  Directions translateInputToGameActions(int userInputAsByte);
}
