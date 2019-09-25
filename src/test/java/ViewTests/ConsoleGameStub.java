package ViewTests;

import Controller.BoardController;
import Controller.EnemyController;
import Controller.IBoardGenerator;
import Controller.IEnemyController;
import ControllerTests.BoardGeneratorStub;
import Model.EntityObjects.Ghost;
import Model.EntityObjects.Pacman;
import View.Console.ConsoleInputAdapter;
import View.IGame;
import java.util.ArrayList;

public class ConsoleGameStub implements IGame {

  private ConsoleInputAdapter consoleInputAdapter = new ConsoleInputAdapter();
  private IBoardGenerator boardGenerator = new BoardGeneratorStub();
  private BoardController boardController = new BoardController(boardGenerator);
  private IEnemyController enemyController;
  private Pacman pacman;
  private ArrayList<Ghost> ghosts;
  private int pacmanScoreToWin;

  @Override
  public void setupGame() {
    boardController.createEntity("Pacman", 2, 2, true);
    pacman = (Pacman) boardController.getExistingEntityByName("Pacman");
    boardController.createEntity("Ghost1", 2, 1, false);
    ghosts = new ArrayList<>();
    ghosts.add((Ghost) boardController.getExistingEntityByName("Ghost1"));

    enemyController = new EnemyControllerStub();
    pacmanScoreToWin = boardGenerator.scoreAmount() - 1;
  }

  @Override
  public boolean isPacmanAliveOrDotsUneaten() {
    return pacman.getCurrentScore() < pacmanScoreToWin && ghosts.get(0).getCurrentScore() < 1;
  }

  @Override
  public void runGame(int userInputAsByte) {
    consoleInputAdapter.translateInputToGameActions(boardController, userInputAsByte, pacman);

    for (Ghost ghost : ghosts) {
      enemyController.moveEnemy(boardController, pacman, ghost);
    }

    if (pacman.getCurrentScore() > pacmanScoreToWin) {
      endGame(true, 1);
    } else if (ghosts.get(0).getCurrentScore() > 0) {
      endGame(false, 1);
    }
  }

  @Override
  public void endGame(Boolean playerWin, int currentLevelIteration) {

  }
}
