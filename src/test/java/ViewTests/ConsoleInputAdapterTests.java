package ViewTests;

import static Utilities.Constants.A_KEY;
import static Utilities.Constants.D_KEY;
import static Utilities.Constants.S_KEY;
import static Utilities.Constants.W_KEY;


import Controller.BoardController;
import Controller.IBoardGenerator;
import ControllerTests.BoardGeneratorStub;
import Model.EntityObjects.Pacman;
import Model.Point;
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
    Pacman pacman = (Pacman) boardController.getExistingEntitiesEntry("Pacman");

    boardController.tryToRotateAndMoveEntity("Pacman", consoleInputAdapter.translateInputToGameActions(W_KEY));
    boardController.alternatePacmanMouth("Pacman");
    Point point = new Point(2, 1);

    Assert.assertEquals("|", boardController.getObjectRepresentationAtPosition(point));
  }

  @Test
  public void aKeyPressMovesPacmanLeft() {
    ConsoleInputAdapter consoleInputAdapter = new ConsoleInputAdapter();
    Pacman pacman = (Pacman) boardController.getExistingEntitiesEntry("Pacman");

    boardController.tryToRotateAndMoveEntity("Pacman", consoleInputAdapter.translateInputToGameActions(A_KEY));
    boardController.alternatePacmanMouth("Pacman");
    Point point = new Point(1, 2);

    Assert.assertEquals("-", boardController.getObjectRepresentationAtPosition(point));
  }

  @Test
  public void dKeyPressMovesPacmanRight() {
    ConsoleInputAdapter consoleInputAdapter = new ConsoleInputAdapter();
    Pacman pacman = (Pacman) boardController.getExistingEntitiesEntry("Pacman");

    boardController.tryToRotateAndMoveEntity("Pacman", consoleInputAdapter.translateInputToGameActions(D_KEY));
    boardController.alternatePacmanMouth("Pacman");
    Point point = new Point(3, 2);

    Assert.assertEquals("-", boardController.getObjectRepresentationAtPosition(point));
  }

  @Test
  public void sKeyPressMovesPacmanDown() {
    ConsoleInputAdapter consoleInputAdapter = new ConsoleInputAdapter();
    Pacman pacman = (Pacman) boardController.getExistingEntitiesEntry("Pacman");

    boardController.tryToRotateAndMoveEntity("Pacman", consoleInputAdapter.translateInputToGameActions(S_KEY));
    boardController.alternatePacmanMouth("Pacman");
    Point point = new Point(2, 3);

    Assert.assertEquals("|", boardController.getObjectRepresentationAtPosition(point));
  }
}
