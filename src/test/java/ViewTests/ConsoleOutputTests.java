package ViewTests;

import Controller.BoardController;
import DataStructures.Directions;
import Model.BoardGeneratorStub;
import Model.IBoardGenerator;
import View.Console.ConsoleOutput;
import org.junit.Test;

public class ConsoleOutputTests {

  @Test
  public void printerPrintsCorrectly() {
    IBoardGenerator boardGeneratorStub = new BoardGeneratorStub();
    BoardController boardController = new BoardController(boardGeneratorStub);
    ConsoleOutput consolePrinter = new ConsoleOutput(boardController);

    consolePrinter.printBoard();
    boardController
        .tryToRotateAndMoveEntity(boardController.getExistingEntityByName("Pacman"), Directions.Up);
    consolePrinter.printBoard();
    boardController
        .tryToRotateAndMoveEntity(boardController.getExistingEntityByName("Pacman"), Directions.Up);
    consolePrinter.printBoard();
    boardController
        .tryToRotateAndMoveEntity(boardController.getExistingEntityByName("Pacman"), Directions.Up);
    consolePrinter.printBoard();
  }
}
