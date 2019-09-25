package View.Console;

import Controller.*;
import Model.EntityObjects.Ghost;
import Model.EntityObjects.Pacman;
import View.IGame;
import View.IGameInput;
import View.IGameOutput;
import java.util.ArrayList;

public class ConsoleGame implements IGame {

  private IGameInput consoleInput;
  private IGameOutput consoleOutput;
  private ConsoleInputAdapter consoleInputAdapter = new ConsoleInputAdapter();
  private IBoardGenerator boardGenerator = new BoardGenerator();
  private BoardController boardController = new BoardController(boardGenerator);
  private IEnemyController enemyController;
  private Pacman pacman;
  private ArrayList<Ghost> ghosts;
  private int pacmanScoreToWin;

  public ConsoleGame(IGameInput consoleInput, IGameOutput consoleOutput) {
    this.consoleInput = consoleInput;
    this.consoleOutput = consoleOutput;
  }

  @Override
  public void setupGame() {
    Runtime runtime = Runtime.getRuntime();
    runtime.addShutdownHook(new ConsoleCleanUp());

    boardController.createEntity("Pacman", boardController.getBoardWidth() / 2, boardController.getBoardHeight() / 2, true);
    boardController.createEntity("Ghost1", 0, 0, false);
    boardController.createEntity("Ghost2", boardController.getBoardWidth() - 1, boardController.getBoardHeight() - 1, false);

    pacman = (Pacman) boardController.getExistingEntityByName("Pacman");
    ghosts = new ArrayList<>();

    int i = 1;
    while (boardController.getExistingEntityByName("Ghost" + i) != null) {
      ghosts.add((Ghost) boardController.getExistingEntityByName("Ghost" + i));
      i++;
    }

    enemyController = new EnemyController();
    pacmanScoreToWin = boardGenerator.scoreAmount() - 1;
  }

  @Override
  public boolean isPacmanAliveOrDotsUneaten() {
    boolean haveAnyGhostsEatenPacman = ghosts.stream().anyMatch(x -> x.getCurrentScore() >= 1);
    return pacman.getCurrentScore() < pacmanScoreToWin && !haveAnyGhostsEatenPacman;
  }

  @Override
  public void runGame(int currentLevelIteration) {
    setupGame();
    int userInputAsByte = 0, rawInput;
    while (isPacmanAliveOrDotsUneaten()) {
      rawInput = consoleInput.getUserInput();

      if (rawInput != 0) {
        userInputAsByte = rawInput;
      }

      consoleInputAdapter.translateInputToGameActions(boardController, userInputAsByte, pacman);

      for (Ghost ghost : ghosts) {
        enemyController.moveEnemy(boardController, pacman, ghost);
      }

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
