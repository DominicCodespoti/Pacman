package ViewTests;

import Controller.Board;
import Controller.IBoardGenerator;
import Controller.PacmanController;
import ControllerTests.BoardGeneratorStub;
import Model.Directions;
import Model.EntityObjects.Pacman;
import Model.Point;
import View.Console.ConsoleInput;
import View.Console.ConsoleOutput;
import View.Game;
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
