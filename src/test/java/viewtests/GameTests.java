package viewtests;

import controller.Board;
import controller.IBoardGenerator;
import controller.PacmanController;
import controllertests.BoardGeneratorStub;
import model.Directions;
import model.entityobjects.Pacman;
import model.Point;
import view.Console.ConsoleOutput;
import view.Game;
import org.junit.Assert;
import org.junit.Test;

public class GameTests {

  private final Point TOP_LEFT = new Point(0, 5);

  private void eatRowHorizontally(PacmanController pacmanController, Directions directions) {
    pacmanController.move(directions);
    pacmanController.move(directions);
    pacmanController.move(directions);
    pacmanController.move(directions);
    pacmanController.move(Directions.Down);
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
    PacmanController pacmanController = new PacmanController(boardController, pacman);

    eatRowHorizontally(pacmanController, Directions.Left);
    eatRowHorizontally(pacmanController, Directions.Right);
    eatRowHorizontally(pacmanController, Directions.Left);
    eatRowHorizontally(pacmanController, Directions.Right);
    eatRowHorizontally(pacmanController, Directions.Left);
    eatRowHorizontally(pacmanController, Directions.Right);

    Assert.assertEquals(22, pacman.getCurrentScore());
  }
}
