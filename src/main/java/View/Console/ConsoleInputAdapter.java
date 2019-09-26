package View.Console;

import static Utilities.Constants.A_KEY;
import static Utilities.Constants.D_KEY;
import static Utilities.Constants.EXIT_KEY;
import static Utilities.Constants.S_KEY;
import static Utilities.Constants.W_KEY;

import Controller.BoardController;
import Model.Directions;
import Model.EntityObjects.Pacman;

public class ConsoleInputAdapter {

  public Directions translateInputToGameActions(int userInputAsByte) {
    switch (userInputAsByte) {
      case A_KEY:
        return Directions.Left;

      case D_KEY:
        return Directions.Right;

      case S_KEY:
        return Directions.Down;

      case W_KEY:
        return Directions.Up;

      case EXIT_KEY:
        System.exit(0);
    }
    return null;
  }
}
