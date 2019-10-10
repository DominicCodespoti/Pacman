package ViewTests;

import Controller.Board;
import Controller.IBoardGenerator;
import Controller.IEnemyController;
import ControllerTests.BoardGeneratorStub;
import Model.EntityObjects.Ghost;
import Model.EntityObjects.Pacman;
import View.Console.ConsoleInputAdapter;
import View.Game;
import View.IGameInput;
import View.IGameOutput;
import java.util.ArrayList;

public class GameStub extends Game {

  private final ConsoleInputAdapter consoleInputAdapter = new ConsoleInputAdapter();
  private final IBoardGenerator boardGenerator = new BoardGeneratorStub();
  private final Board boardController = new Board(boardGenerator);
  private IEnemyController enemyController;
  private Pacman pacman;
  private ArrayList<Ghost> ghosts;
  private int pacmanScoreToWin;

  GameStub(IGameInput consoleInput, IGameOutput consoleOutput) {
    super(consoleInput, consoleOutput);
  }

  @Override
  public void setupGame() {
    boardController.createEntity("Pacman", 2, 2, true);
    pacman = (Pacman) boardController.getExistingEntityByName("Pacman");
    boardController.createEntity("Ghost1", 2, 0, false);
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
    boardController.tryToRotateAndMoveEntity(pacman, consoleInputAdapter.translateInputToGameActions(userInputAsByte));
    boardController.alternatePacmanMouth(pacman);

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
