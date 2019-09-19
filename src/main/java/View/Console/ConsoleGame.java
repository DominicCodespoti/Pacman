package View.Console;

import Controller.BoardController;
import DataStructures.Directions;
import DataStructures.Point;
import Model.BoardGenerator;
import Model.DistanceCalculator;
import Model.EntityObjects.Ghost;
import Model.EntityObjects.Pacman;
import Model.IEntityObject;
import View.IGame;
import java.io.IOException;

public class ConsoleGame implements IGame {

  private ConsoleInput consoleInput = new ConsoleInput();

  private void enterCookedTerminalModeAndExit() {
    String[] cmd = {"/bin/sh", "-c", "stty cooked </dev/tty"};
    try {
      Runtime.getRuntime().exec(cmd).waitFor();
    } catch (InterruptedException | IOException e) {
      e.printStackTrace();
    }
    System.exit(0);
  }

  private void enterRawTerminalMode() {
    String[] cmd = {"/bin/sh", "-c", "stty raw </dev/tty"};
    try {
      Runtime.getRuntime().exec(cmd).waitFor();
    } catch (InterruptedException | IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void runGame() {
    BoardGenerator boardGenerator = new BoardGenerator();
    BoardController boardController = new BoardController(boardGenerator);
    ConsoleOutput consoleOutput = new ConsoleOutput(boardController);
    DistanceCalculator distanceCalculator;
    Pacman player = (Pacman) boardController.getExistingEntityByName("Pacman");
    Ghost enemy = (Ghost) boardController.getExistingEntityByName("Ghost");
    Ghost enemy2 = (Ghost) boardController.getExistingEntityByName("Ghost2");
    boolean isPacmansMouthOpen = true;
    int userInputAsByte = 0;

    enterRawTerminalMode();
    int pacmanScoreToWin = boardGenerator.scoreAmount() - 1;

    while (!player.winCondition(pacmanScoreToWin) && !enemy.winCondition(1)) {
      int uncheckedInput = 0;

      uncheckedInput = consoleInput.getUserInput();

      if (uncheckedInput != 0) {
        userInputAsByte = uncheckedInput;
      }

      if (userInputAsByte == 3) {
        enterCookedTerminalModeAndExit();
      }

      if (userInputAsByte == 119) {
        boardController.tryToRotateAndMoveEntity(player, Directions.Up);
        player.setIsMouthOpenToOpposite();
      }

      if (userInputAsByte == 97) {
        boardController.tryToRotateAndMoveEntity(player, Directions.Left);
        player.setIsMouthOpenToOpposite();
      }

      if (userInputAsByte == 100) {
        boardController.tryToRotateAndMoveEntity(player, Directions.Right);
        player.setIsMouthOpenToOpposite();
      }

      if (userInputAsByte == 115) {
        boardController.tryToRotateAndMoveEntity(player, Directions.Down);
        player.setIsMouthOpenToOpposite();
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

      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    if (player.winCondition(pacmanScoreToWin)) {
      consoleOutput.printVictory();
    } else {
      consoleOutput.printLose();
    }

    enterCookedTerminalModeAndExit();
  }
}
