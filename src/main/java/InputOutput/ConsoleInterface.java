package InputOutput;

import GameLogicTests.BoardController;
import GameLogicTests.Directions;
import GameLogicTests.IEntityObject;
import java.io.IOException;

public class ConsoleInterface {

  private ConsoleInput consoleInput = new ConsoleInput();

  private void enterCookedTerminalModeAndExit() throws IOException, InterruptedException {
    String[] cmd = {"/bin/sh", "-c", "stty cooked </dev/tty"};
    Runtime.getRuntime().exec(cmd).waitFor();
    System.exit(0);
  }

  private void enterRawTerminalMode() throws IOException, InterruptedException {
    String[] cmd = {"/bin/sh", "-c", "stty raw </dev/tty"};
    Runtime.getRuntime().exec(cmd).waitFor();
  }

  public void runGame() throws IOException, InterruptedException {
    BoardController boardController = new BoardController(10, 10);
    ConsoleOutput consoleOutput = new ConsoleOutput(boardController);
    IEntityObject player = boardController.getExistingEntityByName("Pacman");
    IEntityObject enemy = boardController.getExistingEntityByName("Ghost");
    int userInputAsByte = 0;

    enterRawTerminalMode();
    int pacmanScoreToWin = boardController.getBoardHeight() * boardController.getBoardWidth() - 3;

    while (!player.winCondition(pacmanScoreToWin) && !enemy.winCondition(1)) {
      int uncheckedInput = consoleInput.getUserInput();
      if (uncheckedInput != 0) {
        userInputAsByte = uncheckedInput;
      }

      if (userInputAsByte == 3) {
        enterCookedTerminalModeAndExit();
      }

      if (userInputAsByte == 119) {
        boardController.attemptToRotateAndMoveEntity(player, Directions.Up);
      }

      if (userInputAsByte == 97) {
        boardController.attemptToRotateAndMoveEntity(player, Directions.Left);
      }

      if (userInputAsByte == 100) {
        boardController.attemptToRotateAndMoveEntity(player, Directions.Right);
      }

      if (userInputAsByte == 115) {
        boardController.attemptToRotateAndMoveEntity(player, Directions.Down);
      }

      boardController.attemptToRotateAndMoveEntity(enemy, Directions.Left);

      consoleOutput.clearScreen();
      consoleOutput.printBoard();
      Thread.sleep(500);
    }
    consoleOutput.clearScreen();
    enterCookedTerminalModeAndExit();
  }
}
