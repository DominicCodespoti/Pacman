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

  public void translateInputToGameActions(BoardController boardController, int userInputAsByte, Pacman player) {
    switch (userInputAsByte) {
      case A_KEY:
        boardController.tryToRotateAndMoveEntity(player, Directions.Left);
        player.setIsMouthOpenToOpposite();
        return;

      case D_KEY:
        boardController.tryToRotateAndMoveEntity(player, Directions.Right);
        player.setIsMouthOpenToOpposite();
        return;

      case S_KEY:
        boardController.tryToRotateAndMoveEntity(player, Directions.Down);
        player.setIsMouthOpenToOpposite();
        return;

      case W_KEY:
        boardController.tryToRotateAndMoveEntity(player, Directions.Up);
        player.setIsMouthOpenToOpposite();
        return;

      case EXIT_KEY:
        System.exit(0);
    }
  }
}
