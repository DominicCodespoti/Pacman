package View.Console;

import static DataStructures.Constants.A_KEY;
import static DataStructures.Constants.D_KEY;
import static DataStructures.Constants.S_KEY;
import static DataStructures.Constants.W_KEY;

import Controller.BoardController;
import DataStructures.Directions;
import Model.EntityObjects.Pacman;

class ConsoleInputAdapter {

  void translateInputToGame(BoardController boardController, int userInputAsByte, Pacman player) {
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
    }
  }
}
