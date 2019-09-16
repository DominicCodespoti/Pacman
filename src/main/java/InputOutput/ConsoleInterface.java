package InputOutput;

import GameLogicTests.BoardController;
import GameLogicTests.Directions;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInterface {

  private InputStreamReader fileInputStream = new InputStreamReader(System.in);
  private BufferedReader bufferedReader = new BufferedReader(fileInputStream);

  public void enterCookedTerminalMode() throws IOException, InterruptedException {
    String[] cmd1 = {"/bin/sh", "-c", "stty cooked </dev/tty"};
    Runtime.getRuntime().exec(cmd1).waitFor();
    System.exit(0);
  }

  public void runGame() throws IOException, InterruptedException {
    BoardController boardController = new BoardController(5, 5);
    ConsolePrinter consolePrinter = new ConsolePrinter(boardController);

    int userInputAsByte = 0;
    String[] cmd = {"/bin/sh", "-c", "stty raw </dev/tty"};
    Runtime.getRuntime().exec(cmd).waitFor();

    while (!boardController.getExistingEntityByName("Pacman").winCondition(24)) {
      if (bufferedReader.ready()) {
        userInputAsByte = bufferedReader.read();
      }

      if (userInputAsByte == 3) {
        enterCookedTerminalMode();
      }

      if (userInputAsByte == 119) {
        boardController.getExistingEntityByName("Pacman").updateCurrentDirection(Directions.Up);
        boardController.attemptToMoveEntity(boardController.getExistingEntityByName("Pacman"));
        userInputAsByte = 0;
      }

      if (userInputAsByte == 97) {
        boardController.getExistingEntityByName("Pacman").updateCurrentDirection(Directions.Left);
        boardController.attemptToMoveEntity(boardController.getExistingEntityByName("Pacman"));
        userInputAsByte = 0;
      }

      if (userInputAsByte == 100) {
        boardController.getExistingEntityByName("Pacman").updateCurrentDirection(Directions.Right);
        boardController.attemptToMoveEntity(boardController.getExistingEntityByName("Pacman"));
        userInputAsByte = 0;
      }

      if (userInputAsByte == 115) {
        boardController.getExistingEntityByName("Pacman").updateCurrentDirection(Directions.Down);
        boardController.attemptToMoveEntity(boardController.getExistingEntityByName("Pacman"));
        userInputAsByte = 0;
      }

      System.out.print("\033[H\033[2J");
      consolePrinter.printBoard();
      Thread.sleep(1000);
    }
    enterCookedTerminalMode();
  }
}
