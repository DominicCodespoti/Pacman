package ViewTests;

import Controller.Board;
import Controller.IBoardGenerator;
import Controller.PacmanController;
import ControllerTests.BoardGeneratorStub;
import Model.Directions;
import Model.EntityObjects.Pacman;
import Model.Point;
import View.Console.ConsoleInput;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ConsoleInputAdapterTests {

  private Board boardController;
  private PacmanController pacmanController;
  private final Point MIDDLE_MIDDLE = new Point(2, 2);

  @Before
  public void initializeBoard() {
    IBoardGenerator boardGeneratorStub = new BoardGeneratorStub();
    boardController = new Board(boardGeneratorStub);
    Pacman pacman = boardController.createPacman( "Pacman", MIDDLE_MIDDLE);
    pacmanController = new PacmanController(boardController, pacman);
  }

  @Test
  public void wKeyPressMovesPacmanUp() {
    ConsoleInput consoleInputAdapter = new ConsoleInput();

    pacmanController.move(consoleInputAdapter.getUserInput(Directions.Up));
    Point point = new Point(2, 1);

    Assert.assertEquals("|", boardController.getObjectRepresentationAtPosition(point));
  }

  @Test
  public void aKeyPressMovesPacmanLeft() {
    ConsoleInput consoleInputAdapter = new ConsoleInput();

    pacmanController.move(consoleInputAdapter.getUserInput(Directions.Left));
    Point point = new Point(1, 2);

    Assert.assertEquals("-", boardController.getObjectRepresentationAtPosition(point));
  }

  @Test
  public void dKeyPressMovesPacmanRight() {
    ConsoleInput consoleInputAdapter = new ConsoleInput();

    pacmanController.move(consoleInputAdapter.getUserInput(Directions.Right));
    Point point = new Point(3, 2);

    Assert.assertEquals("-", boardController.getObjectRepresentationAtPosition(point));
  }

  @Test
  public void sKeyPressMovesPacmanDown() {
    ConsoleInput consoleInputAdapter = new ConsoleInput();

    pacmanController.move(consoleInputAdapter.getUserInput(Directions.Down));
    Point point = new Point(2, 3);

    Assert.assertEquals("|", boardController.getObjectRepresentationAtPosition(point));
  }
}
