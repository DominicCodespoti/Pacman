package View.Console;

import Controller.BoardController;
import Controller.EnemyController;
import Model.BoardGenerator;
import Model.EntityObjects.Ghost;
import Model.EntityObjects.Pacman;
import View.IGame;
import View.IGameInput;
import View.IGameOutput;

public class ConsoleGame implements IGame {

  private IGameInput consoleInput;
  private IGameOutput consoleOutput;
  private ConsoleInputAdapter consoleInputAdapter = new ConsoleInputAdapter();
  private BoardGenerator boardGenerator = new BoardGenerator();
  private BoardController boardController = new BoardController(boardGenerator);
  private EnemyController enemyController;
  private Pacman pacman;
  private Ghost ghostOne, ghostTwo;
  private int pacmanScoreToWin;

  public ConsoleGame(IGameInput consoleInput, IGameOutput consoleOutput) {
    this.consoleInput = consoleInput;
    this.consoleOutput = consoleOutput;
  }

  @Override
  public void setupGame() {
    Runtime runtime = Runtime.getRuntime();
    runtime.addShutdownHook(new ConsoleCleanUp());

    pacman = (Pacman) boardController.getExistingEntityByName("Pacman");
    ghostOne = (Ghost) boardController.getExistingEntityByName("Ghost");
    ghostTwo = (Ghost) boardController.getExistingEntityByName("Ghost2");
    enemyController = new EnemyController();

    pacmanScoreToWin = boardGenerator.scoreAmount() - 1;
  }

  @Override
  public void runGame(int currentLevelIteration) {
    setupGame();
    int userInputAsByte = 0, rawInput;
    while (pacman.getCurrentScore() < pacmanScoreToWin && ghostOne.getCurrentScore() < 1
        && ghostTwo.getCurrentScore() < 1) {
      rawInput = consoleInput.getUserInput();

      if (rawInput != 0) {
        userInputAsByte = rawInput;
      }

      consoleInputAdapter.translateInputToGame(boardController, userInputAsByte, pacman);

      enemyController.moveEnemy(boardController, pacman, ghostOne);
      enemyController.moveEnemy(boardController, pacman, ghostTwo);

      consoleOutput.printBoard(boardController);
      consoleOutput.printScore(boardController.getEntityScore(pacman), currentLevelIteration);

      try {
        Thread.sleep(200);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    endGame(pacman.getCurrentScore() >= pacmanScoreToWin, currentLevelIteration);
  }

  @Override
  public void endGame(Boolean playerWin, int currentLevelIteration) {
    if (playerWin) {
      consoleOutput.printVictory();
      ConsoleGame newGame = new ConsoleGame(consoleInput, consoleOutput);
      newGame.runGame(++currentLevelIteration);
    } else {
      consoleOutput.printLose();
    }
  }
}
