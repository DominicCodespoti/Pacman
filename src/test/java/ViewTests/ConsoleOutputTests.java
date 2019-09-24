package ViewTests;

import Controller.BoardController;
import Controller.IBoardGenerator;
import ControllerTests.BoardGeneratorStub;
import Model.Directions;
import View.Console.ConsoleOutput;
import org.junit.Test;

public class ConsoleOutputTests {

  @Test
  public void printerPrintsCorrectly() {
    IBoardGenerator boardGeneratorStub = new BoardGeneratorStub();
    BoardController boardController = new BoardController(boardGeneratorStub);
    ConsoleOutput consolePrinter = new ConsoleOutput();

    boardController
        .createEntity("Pacman", boardController.getBoardWidth() / 2, boardController.getBoardHeight() / 2, true);
    boardController.createEntity("Ghost1", 0, 0, false);
    boardController
        .createEntity("Ghost2", boardController.getBoardWidth() - 1, boardController.getBoardHeight() - 1, false);

    consolePrinter.printBoard(boardController);
    boardController.tryToRotateAndMoveEntity(boardController.getExistingEntityByName("Pacman"), Directions.Up);
    consolePrinter.printBoard(boardController);
    boardController.tryToRotateAndMoveEntity(boardController.getExistingEntityByName("Pacman"), Directions.Up);
    consolePrinter.printBoard(boardController);
    boardController.tryToRotateAndMoveEntity(boardController.getExistingEntityByName("Pacman"), Directions.Up);
    consolePrinter.printBoard(boardController); //TODO: MOCK OUTPUT
  }
}
