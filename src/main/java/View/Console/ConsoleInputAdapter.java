package View.Console;

import static Utilities.Constants.LEFT_INPUT;
import static Utilities.Constants.RIGHT_INPUT;
import static Utilities.Constants.EXIT_INPUT;
import static Utilities.Constants.DOWN_INPUT;
import static Utilities.Constants.UP_INPUT;


import Model.Directions;

public class ConsoleInputAdapter {

  public Directions translateInputToGameActions(int userInputAsByte) {
    switch (userInputAsByte) {
      case LEFT_INPUT:
        return Directions.Left;

      case RIGHT_INPUT:
        return Directions.Right;

      case DOWN_INPUT:
        return Directions.Down;

      case UP_INPUT:
        return Directions.Up;

      case EXIT_INPUT:
        System.exit(0);
    }
    return null;
  }
}
