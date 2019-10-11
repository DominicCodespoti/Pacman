package View;

import static Utilities.Constants.UP_INPUT;


import Controller.Board;
import Controller.EnemyController;
import Controller.IBoardGenerator;
import Controller.PacmanController;
import Model.Directions;
import Model.EntityObjects.Ghost;
import Model.EntityObjects.Pacman;
import Model.Point;
import Utilities.DistanceCalculator;
import java.util.ArrayList;
import java.util.List;

public class Game {

  private final IGameInput consoleInput;
  private final IGameOutput consoleOutput;
  private final IBoardGenerator boardGenerator;
  private final Board board;
  private int userInputAsByte = UP_INPUT;
  private PacmanController pacmanController;
  private Pacman pacman;
  private List<Ghost> ghost = new ArrayList<>();
  private int pacmanScoreToWin;

  public Game(IGameInput consoleInput, IGameOutput consoleOutput, IBoardGenerator boardGenerator) {
    this.consoleInput = consoleInput;
    this.consoleOutput = consoleOutput;
    this.boardGenerator = boardGenerator;
    board = new Board(boardGenerator);
  }

  public void runGame(int currentLevelIteration , int ghostAmount) {
    setupGame(ghostAmount);
    while (isPacmanAliveOrDotsUneaten()) {
      getInput();
      moveEntities(userInputAsByte);
      consoleOutput.printBoard(board);
      consoleOutput.printScore(pacman.getCurrentScore(), currentLevelIteration);
    }
    endGame(pacman.getCurrentScore() >= pacmanScoreToWin, currentLevelIteration);
  }

  private void setupGame(int ghostAmount) {
    Point pacmanInitialPosition = new Point(board.getBoardWidth() / 2, board.getBoardHeight() / 2);
    pacman = board.createPacman("Pacman", pacmanInitialPosition);
    for (int i = 0; i < ghostAmount; i++) {
      Point ghostInitialPosition = new Point(i, 0);
      ghost.add(board.createGhost("Ghost", ghostInitialPosition));
    }
    pacmanController = new PacmanController(board, pacman);
    pacmanScoreToWin = boardGenerator.scoreAmount();
  }

  public boolean isPacmanAliveOrDotsUneaten() {
    boolean haveAnyGhostsEatenPacman = ghost.stream().anyMatch(x -> x.getCurrentScore() >= 1);
    return pacman.getCurrentScore() < pacmanScoreToWin && !haveAnyGhostsEatenPacman;
  }

  private void getInput() {
    int tempInput;
    tempInput = consoleInput.getUserInput();

    if (tempInput != 0) {
      userInputAsByte = tempInput;
    }
  }

  private void moveEntities(int userInputAsByte) {
    pacmanController.move(consoleInput.translateInputToGameActions(userInputAsByte));
    for (Ghost value : ghost) {
      Point ghostPos = board.getExistingEntityPosition(value);
      Point pacmanPos = board.getExistingEntityPosition(pacman);
      Directions fastestDirection = DistanceCalculator.findDirectionWithClosestPath(pacmanPos, ghostPos);
      EnemyController enemyController = new EnemyController(board, value);
      enemyController.move(fastestDirection);
    }
  }

  private void endGame(Boolean playerWin, int currentLevelIteration) {
    if (playerWin) {
      consoleOutput.printVictory();
      Game newGame = new Game(consoleInput, consoleOutput, boardGenerator);
      newGame.runGame(++currentLevelIteration, ghost.size());
    } else {
      consoleOutput.printLose();
    }
  }
}
