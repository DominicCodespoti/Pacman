package View.Console;

import Controller.BoardController;
import DataStructures.Directions;
import DataStructures.Point;
import Model.BoardGenerator;
import Model.DistanceCalculator;
import Model.IEntityObject;
import View.IView;
import java.io.IOException;

public class ConsoleView implements IView {

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

  @Override
  public void runGame() throws IOException, InterruptedException {
    BoardGenerator boardGenerator = new BoardGenerator();
    BoardController boardController = new BoardController(boardGenerator);
    ConsoleOutput consoleOutput = new ConsoleOutput(boardController);
    DistanceCalculator distanceCalculator;
    IEntityObject player = boardController.getExistingEntityByName("Pacman");
    IEntityObject enemy = boardController.getExistingEntityByName("Ghost");
    IEntityObject enemy2 = boardController.getExistingEntityByName("Ghost2");
    int userInputAsByte = 0;

    enterRawTerminalMode();
    int pacmanScoreToWin = boardGenerator.scoreAmount() - 1;

    while (!player.winCondition(pacmanScoreToWin) && !enemy.winCondition(1)) {
      int uncheckedInput = consoleInput.getUserInput();
      if (uncheckedInput != 0) {
        userInputAsByte = uncheckedInput;
      }

      if (userInputAsByte == 3) {
        enterCookedTerminalModeAndExit();
      }

      if (userInputAsByte == 119) {
        boardController.tryToRotateAndMoveEntity(player, Directions.Up);
      }

      if (userInputAsByte == 97) {
        boardController.tryToRotateAndMoveEntity(player, Directions.Left);
      }

      if (userInputAsByte == 100) {
        boardController.tryToRotateAndMoveEntity(player, Directions.Right);
      }

      if (userInputAsByte == 115) {
        boardController.tryToRotateAndMoveEntity(player, Directions.Down);
      }

      if (boardController.getExistingEntityByName("Pacman") != null) {
        Point playerCurrentPosition = boardController.getExistingEntityPosition(player);
        Point enemyCurrentPosition = boardController.getExistingEntityPosition(enemy);
        Point enemy2CurrentPosition = boardController.getExistingEntityPosition(enemy);

        distanceCalculator = new DistanceCalculator(enemyCurrentPosition);
        boardController.tryToRotateAndMoveEntity(enemy,
            distanceCalculator.findDirectionWithClosestPath(playerCurrentPosition));
        distanceCalculator = new DistanceCalculator(enemy2CurrentPosition);
        boardController.tryToRotateAndMoveEntity(enemy2,
            distanceCalculator.findDirectionWithClosestPath(playerCurrentPosition));
      }

      consoleOutput.printBoard();
      Thread.sleep(500);
    }

    if (player.winCondition(pacmanScoreToWin)) {
      consoleOutput.printVictory();
    } else {
      consoleOutput.printLose();
    }
    enterCookedTerminalModeAndExit();
  }
}
