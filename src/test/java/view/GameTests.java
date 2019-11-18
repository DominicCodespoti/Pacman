package view;

import world.Board;
import world.IBoardGenerator;
import world.BoardGeneratorStub;
import world.Direction;
import gameobjects.Pacman;
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

  // rename: gameIsOverWhenPacmanDies
  @Test
  public void doesWinConditionDetectPacmanDying() {
    Game consoleGame = new Game(new StubInput(), new ConsoleOutput(), new BoardGeneratorStub());
    consoleGame.runGame();
    // it's not clear why this works - is it because no pacman was created on the board?
    Assert.assertFalse(consoleGame.isPacmanAliveOrDotsUneaten());
  }

  // rename: pacmanWinsLevelWhenAllDotsAreEaten
  @Test
  public void doesPacmanEatingAllDotsFulfillWinCriteria() {
    // It's hard to tell what's happening in this test.
    // Some suggestions in the comments below.

    // replace:
    // IBoardGenerator boardGenerator = new BoardGeneratorStub();
    // Board board = new Board(boardGenerator);
    // with:
    // board = TestBoardGenerator.new5x5BoardWithWallAtBottomLeft()

    // Instead of needing pacman to eat all dots in all rows, you could initialise
    // the board with one dot remaining, get pacman to eat it, then assert that the
    // game is over/won.
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
    // I expect to see an assertion for a 'win' here, based on the test name
  }

  // Idea for another test: Pacman goes to next level when all dots are eaten (if that's right...).
}
