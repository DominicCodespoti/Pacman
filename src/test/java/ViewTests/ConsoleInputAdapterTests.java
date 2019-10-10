package ViewTests;

import static Utilities.Constants.DOWN_INPUT;
import static Utilities.Constants.LEFT_INPUT;
import static Utilities.Constants.RIGHT_INPUT;
import static Utilities.Constants.UP_INPUT;

import Controller.Board;
import Controller.IBoardGenerator;
import Controller.PacmanController;
import ControllerTests.BoardGeneratorStub;
import Model.EntityObjects.Pacman;
import Model.Point;
import View.Console.ConsoleInputAdapter;
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
    Pacman pacman = (Pacman) boardController.createEntity("Pacman", "Pacman", MIDDLE_MIDDLE);
    pacmanController = new PacmanController(boardController, pacman);
  }

  @Test
  public void wKeyPressMovesPacmanUp() {
    ConsoleInputAdapter consoleInputAdapter = new ConsoleInputAdapter();

    pacmanController.move(consoleInputAdapter.translateInputToGameActions(UP_INPUT));
    Point point = new Point(2, 1);

    Assert.assertEquals("|", boardController.getObjectRepresentationAtPosition(point));
  }

  @Test
  public void aKeyPressMovesPacmanLeft() {
    ConsoleInputAdapter consoleInputAdapter = new ConsoleInputAdapter();

    pacmanController.move(consoleInputAdapter.translateInputToGameActions(LEFT_INPUT));
    Point point = new Point(1, 2);

    Assert.assertEquals("-", boardController.getObjectRepresentationAtPosition(point));
  }

  @Test
  public void dKeyPressMovesPacmanRight() {
    ConsoleInputAdapter consoleInputAdapter = new ConsoleInputAdapter();

    pacmanController.move(consoleInputAdapter.translateInputToGameActions(RIGHT_INPUT));
    Point point = new Point(3, 2);

    Assert.assertEquals("-", boardController.getObjectRepresentationAtPosition(point));
  }

  @Test
  public void sKeyPressMovesPacmanDown() {
    ConsoleInputAdapter consoleInputAdapter = new ConsoleInputAdapter();

    pacmanController.move(consoleInputAdapter.translateInputToGameActions(DOWN_INPUT));
    Point point = new Point(2, 3);

    Assert.assertEquals("|", boardController.getObjectRepresentationAtPosition(point));
  }
}
