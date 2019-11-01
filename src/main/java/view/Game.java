package view;

import controller.Board;
import controller.GhostController;
import controller.IBoardGenerator;
import controller.PacmanController;
import model.Directions;
import model.EntityObjects.Ghost;
import model.EntityObjects.Pacman;
import model.Point;
import utilities.DistanceCalculator;
import java.util.ArrayList;
import java.util.List;

public class Game {

  private static final String PACMAN = "Pacman";
  private static final String GHOST = "Ghost";
  private final IGameInput consoleInput;
  private final IGameOutput consoleOutput;
  private final IBoardGenerator boardGenerator;
  private Board board;
  private PacmanController pacmanController;
  private Pacman pacman;
  private final List<Ghost> ghost = new ArrayList<>();
  private int pacmanScoreToWin;
  private int currentLevelIteration = 1;
  private Directions lastDirection = Directions.Up;

  public Game(IGameInput consoleInput, IGameOutput consoleOutput, IBoardGenerator boardGenerator) {
    this.consoleInput = consoleInput;
    this.consoleOutput = consoleOutput;
    this.boardGenerator = boardGenerator;
    board = new Board(boardGenerator);
  }

  public void runGame() {
    setupGame();
    while (isPacmanAliveOrDotsUneaten()) {
      moveEntities();
      consoleOutput.printBoard(board, pacman.getCurrentScore(), currentLevelIteration);
    }
    endGame(pacman.getCurrentScore() >= pacmanScoreToWin);
  }

  private void setupGame() {
    board = new Board(boardGenerator);
    Point pacmanInitialPosition = new Point(board.getBoardWidth() / 2, board.getBoardHeight() / 2);
    pacman = board.createPacman(PACMAN, pacmanInitialPosition);
    for (int i = 0; i < 2; i++) {
      Point ghostInitialPosition = new Point(i, 0);
      ghost.add(board.createGhost(GHOST + i, ghostInitialPosition));
    }
    pacmanController = new PacmanController(board, pacman);
    pacmanScoreToWin = boardGenerator.scoreAmount();
  }

  public boolean isPacmanAliveOrDotsUneaten() {
    boolean haveAnyGhostsEatenPacman = ghost.stream().anyMatch(x -> x.getCurrentScore() >= 1);
    return pacman.getCurrentScore() < pacmanScoreToWin && !haveAnyGhostsEatenPacman;
  }

  private void moveEntities() {
    movePacman();
    moveGhosts();
  }

  private void movePacman() {
    lastDirection = consoleInput.getUserInput(lastDirection);
    pacmanController.move(lastDirection);
  }

  private void moveGhosts() {
    for (Ghost value : ghost) {
      if (board.getExistingEntityByName(pacman.getName()) != null) {
        Point ghostPos = board.getExistingEntityPosition(value);
        Point pacmanPos = board.getExistingEntityPosition(pacman);
        Directions fastestDirection = DistanceCalculator.findDirectionWithClosestPath(pacmanPos, ghostPos);
        GhostController ghostController = new GhostController(board, value);
        ghostController.move(fastestDirection);
      }
    }
  }

  private void endGame(Boolean playerWin) {
    if (playerWin) {
      consoleOutput.printVictory();
      currentLevelIteration++;
      setupGame();
      runGame();
    } else {
      consoleOutput.printLose();
    }
  }
}
