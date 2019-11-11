package view;

import controller.Board;
import controller.IBoardGenerator;
import java.util.ArrayList;
import java.util.List;
import model.Direction;
import model.Point;
import model.entityobjects.Ghost;
import model.entityobjects.Pacman;
import utilities.DistanceCalculator;

public class Game {

  private static final String PACMAN = "Pacman";
  private static final String GHOST = "Ghost";
  private final IGameInput consoleInput;
  private final IGameOutput consoleOutput;
  private final IBoardGenerator boardGenerator;
  private Board board;
  private Pacman pacman;
  private final List<Ghost> ghosts = new ArrayList<>();
  private int currentLevelIteration = 1;
  private Direction lastDirection = Direction.Up;

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
    endGame();
  }

  private void setupGame() {
    board = new Board(boardGenerator);

    Point middleOfBoard = new Point(board.getBoardWidth() / 2, board.getBoardHeight() / 2);
    pacman = board.createPacman(PACMAN, middleOfBoard);

    for (int i = 0; i < 2; i++) {
      Point ghostInitialPosition = new Point(i, 0);
      ghosts.add(board.createGhost(GHOST + i, ghostInitialPosition));
    }
  }

  public boolean isPacmanAliveOrDotsUneaten() {
    boolean haveAnyGhostsEatenPacman = ghosts.stream().anyMatch(x -> x.getCurrentScore() >= 1);
    return pacman.getCurrentScore() < boardGenerator.scoreAmount() && !haveAnyGhostsEatenPacman;
  }

  private void moveEntities() {
    movePacman();
    moveGhosts();
  }

  private void movePacman() {
    lastDirection = consoleInput.getUserInput(lastDirection);
    pacman.move(lastDirection, board);
  }

  private void moveGhosts() {
    Point pacmanPos = board.getPosition(pacman);
    for (Ghost currentGhost : ghosts) {
      Point ghostPos = board.getPosition(currentGhost);
      Direction fastestDirection = DistanceCalculator.findFastestDirection(pacmanPos, ghostPos);
      currentGhost.move(fastestDirection, board);
    }
  }

  private void endGame() {
    boolean playerWin = pacman.getCurrentScore() >= boardGenerator.scoreAmount();
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
