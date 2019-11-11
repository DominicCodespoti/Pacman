package viewtests;

import controller.Board;
import controller.IBoardGenerator;
import controllertests.BoardGeneratorStub;
import model.Direction;
import model.entityobjects.Pacman;
import model.Point;
import view.console.ConsoleOutput;
import view.Game;
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
    Board boardController = new Board(boardGenerator);

    Pacman pacman = boardController.createPacman("Pacman", TOP_LEFT);

    eatRowHorizontally(pacman, Direction.Left, boardController);
    eatRowHorizontally(pacman, Direction.Right, boardController);
    eatRowHorizontally(pacman, Direction.Left, boardController);
    eatRowHorizontally(pacman, Direction.Right, boardController);
    eatRowHorizontally(pacman, Direction.Left, boardController);
    eatRowHorizontally(pacman, Direction.Right, boardController);
    eatRowHorizontally(pacman, Direction.Right, boardController);
    eatRowHorizontally(pacman, Direction.Left, boardController);
    eatRowHorizontally(pacman, Direction.Right, boardController);

    Assert.assertEquals(22, pacman.getCurrentScore());
  }
}
