package view;

import world.Board;
import world.IBoardGenerator;
import world.BoardGeneratorStub;
import world.Direction;
import entities.Pacman;
import world.Point;
import view.console.ConsoleOutput;
import org.junit.Assert;
import org.junit.Test;

public class GameTests {

  private final Point TOP_LEFT = new Point(0, 5);

  private void eatRowHorizontally(Pacman pacman, Direction direction, Board board) {
    pacman.move(direction, board);
    pacman.move(direction, board);
    pacman.move(direction, board);
    pacman.move(direction, board);
    pacman.move(Direction.Down, board);
  }

  @Test
  public void doesWinConditionDetectPacmanDying() {
    Game consoleGame = new Game(new StubInput(), new ConsoleOutput(), new BoardGeneratorStub());
    consoleGame.runGame();
    Assert.assertFalse(consoleGame.isPacmanAliveOrDotsUneaten());
  }

  @Test
  public void doesPacmanEatingAllDotsFulfillWinCriteria() {
    IBoardGenerator boardGenerator = new BoardGeneratorStub();
    Board board = new Board(boardGenerator);

    Pacman pacman = board.createPacman("Pacman", TOP_LEFT);

    eatRowHorizontally(pacman, Direction.Left, board);
    eatRowHorizontally(pacman, Direction.Right, board);
    eatRowHorizontally(pacman, Direction.Left, board);
    eatRowHorizontally(pacman, Direction.Right, board);
    eatRowHorizontally(pacman, Direction.Left, board);
    eatRowHorizontally(pacman, Direction.Right, board);
    eatRowHorizontally(pacman, Direction.Right, board);
    eatRowHorizontally(pacman, Direction.Left, board);
    eatRowHorizontally(pacman, Direction.Right, board);

    Assert.assertEquals(22, pacman.getCurrentScore());
  }
}
