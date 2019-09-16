package InputOutput;

import GameLogicTests.BoardController;
import GameLogicTests.Directions;
import GameLogicTests.IEntityObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInterface {

  private InputStreamReader fileInputStream = new InputStreamReader(System.in);
  private BufferedReader bufferedReader = new BufferedReader(fileInputStream);

  public void enterCookedTerminalModeAndExit() throws IOException, InterruptedException {
    String[] cmd1 = {"/bin/sh", "-c", "stty cooked </dev/tty"};
    Runtime.getRuntime().exec(cmd1).waitFor();
    System.exit(0);
  }

  public void runGame() throws IOException, InterruptedException {
    BoardController boardController = new BoardController(5, 5);
    ConsolePrinter consolePrinter = new ConsolePrinter(boardController);

    int userInputAsByte = 0;
    IEntityObject player = boardController.getExistingEntityByName("Pacman");
    String[] cmd = {"/bin/sh", "-c", "stty raw </dev/tty"};
    Runtime.getRuntime().exec(cmd).waitFor();

    while (!boardController.getExistingEntityByName("Pacman").winCondition(24)) {
      if (bufferedReader.ready()) {
        userInputAsByte = bufferedReader.read();
      }

      if (userInputAsByte == 3) {
        enterCookedTerminalModeAndExit();
      }

      if (userInputAsByte == 119) {
        boardController.attemptToRotateEntity(player, Directions.Up);
        boardController.attemptToMoveEntity(player);
        userInputAsByte = 0;
      }

      if (userInputAsByte == 97) {
        boardController.attemptToRotateEntity(player, Directions.Left);
        boardController.attemptToMoveEntity(player);
        userInputAsByte = 0;
      }

      if (userInputAsByte == 100) {
        boardController.attemptToRotateEntity(player, Directions.Right);
        boardController.attemptToMoveEntity(player);
        userInputAsByte = 0;
      }

      if (userInputAsByte == 115) {
        boardController.attemptToRotateEntity(player, Directions.Down);
        boardController.attemptToMoveEntity(player);
        userInputAsByte = 0;
      }

      System.out.print("\033[H\033[2J");
      consolePrinter.printBoard();
      Thread.sleep(1000);
    }
    enterCookedTerminalModeAndExit();
  }
}
