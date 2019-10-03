package ViewTests;

import static Utilities.Constants.LEFT_INPUT;
import static Utilities.Constants.RIGHT_INPUT;
import static Utilities.Constants.DOWN_INPUT;
import static Utilities.Constants.UP_INPUT;

import Controller.BoardController;
import Model.Point;
import ControllerTests.BoardGeneratorStub;
import Model.EntityObjects.Pacman;
import Controller.IBoardGenerator;
import View.Console.ConsoleInputAdapter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ConsoleInputAdapterTests {

  private BoardController boardController;

  @Before
  public void initializeBoard(){
    IBoardGenerator boardGeneratorStub = new BoardGeneratorStub();
    boardController = new BoardController(boardGeneratorStub);
    boardController.createEntity("Pacman", boardController.getBoardWidth() / 2, boardController.getBoardHeight() / 2, true);
    boardController.createEntity("Ghost1", 0, 0, false);
    boardController.createEntity("Ghost2", boardController.getBoardWidth() - 1, boardController.getBoardHeight() - 1, false);
  }

  @Test
  public void wKeyPressMovesPacmanUp() {
    ConsoleInputAdapter consoleInputAdapter = new ConsoleInputAdapter();
    Pacman pacman = (Pacman) boardController.getExistingEntityByName("Pacman");

    boardController.tryToRotateAndMoveEntity(pacman, consoleInputAdapter.translateInputToGameActions(UP_INPUT));
    boardController.alternatePacmanMouth(pacman);
    Point point = new Point(2, 1);

    Assert.assertEquals("|", boardController.getObjectRepresentationAtPosition(point));
  }

  @Test
  public void aKeyPressMovesPacmanLeft() {
    ConsoleInputAdapter consoleInputAdapter = new ConsoleInputAdapter();
    Pacman pacman = (Pacman) boardController.getExistingEntityByName("Pacman");

    boardController.tryToRotateAndMoveEntity(pacman, consoleInputAdapter.translateInputToGameActions(LEFT_INPUT));
    boardController.alternatePacmanMouth(pacman);
    Point point = new Point(1, 2);

    Assert.assertEquals("-", boardController.getObjectRepresentationAtPosition(point));
  }

  @Test
  public void dKeyPressMovesPacmanRight() {
    ConsoleInputAdapter consoleInputAdapter = new ConsoleInputAdapter();
    Pacman pacman = (Pacman) boardController.getExistingEntityByName("Pacman");

    boardController.tryToRotateAndMoveEntity(pacman, consoleInputAdapter.translateInputToGameActions(RIGHT_INPUT));
    boardController.alternatePacmanMouth(pacman);
    Point point = new Point(3, 2);

    Assert.assertEquals("-", boardController.getObjectRepresentationAtPosition(point));
  }

  @Test
  public void sKeyPressMovesPacmanDown() {
    ConsoleInputAdapter consoleInputAdapter = new ConsoleInputAdapter();
    Pacman pacman = (Pacman) boardController.getExistingEntityByName("Pacman");

    boardController.tryToRotateAndMoveEntity(pacman, consoleInputAdapter.translateInputToGameActions(DOWN_INPUT));
    boardController.alternatePacmanMouth(pacman);
    Point point = new Point(2, 3);

    Assert.assertEquals("|", boardController.getObjectRepresentationAtPosition(point));
  }
}
