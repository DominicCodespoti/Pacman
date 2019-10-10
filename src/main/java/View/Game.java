package View;

import static Utilities.Constants.UP_INPUT;

import Controller.Board;
import Controller.BoardGenerator;
import Controller.EnemyController;
import Controller.IBoardGenerator;
import Controller.PacmanController;
import Model.Directions;
import Model.EntityObjects.Ghost;
import Model.EntityObjects.Pacman;
import Model.Point;
import Utilities.DistanceCalculator;
import View.Console.ConsoleInputAdapter;

public class Game {

  private final IGameInput consoleInput;
  private final IGameOutput consoleOutput;
  private final ConsoleInputAdapter consoleInputAdapter = new ConsoleInputAdapter();
  private final IBoardGenerator boardGenerator = new BoardGenerator();
  private final Board board = new Board(boardGenerator);
  private int userInputAsByte = UP_INPUT;

  private PacmanController pacmanController;

  private Pacman pacman;
  private Ghost ghost;
  private int pacmanScoreToWin;

  public Game(IGameInput consoleInput, IGameOutput consoleOutput) {
    this.consoleInput = consoleInput;
    this.consoleOutput = consoleOutput;
  }

  public void setupGame() {
    Point pacmanInitialPosition = new Point(board.getBoardWidth() / 2, board.getBoardHeight() / 2);
    pacman = (Pacman) board.createEntity("Pacman", "Pacman", pacmanInitialPosition);
    Point ghostInitialPosition = new Point(0, 0);
    ghost = (Ghost) board.createEntity("Ghost", "Ghost", ghostInitialPosition);
    pacmanController = new PacmanController(board, pacman);
    pacmanScoreToWin = boardGenerator.scoreAmount();
  }

  public boolean isPacmanAliveOrDotsUneaten() {
    boolean haveAnyGhostsEatenPacman = ghost.getCurrentScore() >= 1;
    return pacman.getCurrentScore() < pacmanScoreToWin && !haveAnyGhostsEatenPacman;
  }

  public void runGame(int currentLevelIteration) {
    setupGame();
    while (isPacmanAliveOrDotsUneaten()) {
      getInput();
      moveEntities(userInputAsByte);
      consoleOutput.printBoard(board);
      consoleOutput.printScore(pacman.getCurrentScore(), currentLevelIteration);
    }
    endGame(pacman.getCurrentScore() >= pacmanScoreToWin, currentLevelIteration);
  }

  private void getInput() {
    int tempInput;
    tempInput = consoleInput.getUserInput();

    if (tempInput != 0) {
      userInputAsByte = tempInput;
    }
  }

  private void moveEntities(int userInputAsByte) {
    pacmanController.move(consoleInputAdapter.translateInputToGameActions(userInputAsByte));

    Point ghostPos = board.getExistingEntityPosition(ghost);
    Point pacmanPos = board.getExistingEntityPosition(pacman);
    Directions fastestDirection = DistanceCalculator.findDirectionWithClosestPath(pacmanPos, ghostPos);
    EnemyController enemyController = new EnemyController(board, ghost); //strat pattern
    enemyController.move(fastestDirection);
  }

  public void endGame(Boolean playerWin, int currentLevelIteration) {
    if (playerWin) {
      consoleOutput.printVictory();
      Game newGame = new Game(consoleInput, consoleOutput);
      newGame.runGame(++currentLevelIteration);
    } else {
      consoleOutput.printLose();
    }
  }
}
