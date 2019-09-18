package ViewTests;

import Controller.BoardController;
import DataStructures.Directions;
import Model.BoardGeneratorStub;
import View.Console.ConsoleOutput;
import org.junit.Test;

public class ConsolePrinterTests {

  private ConsoleOutput consolePrinter;
  private BoardController testMaster;

  @Test
  public void printerPrintsCorrectly() {
    BoardGeneratorStub boardGeneratorStub = new BoardGeneratorStub();
    testMaster = new BoardController(boardGeneratorStub);
    consolePrinter = new ConsoleOutput(testMaster);

    consolePrinter.printBoard();
    testMaster.tryToRotateAndMoveEntity(testMaster.getExistingEntityByName("Pacman"), Directions.Up);
    consolePrinter.printBoard();
    testMaster.tryToRotateAndMoveEntity(testMaster.getExistingEntityByName("Pacman"), Directions.Up);
    consolePrinter.printBoard();
    testMaster.tryToRotateAndMoveEntity(testMaster.getExistingEntityByName("Pacman"), Directions.Up);
    consolePrinter.printBoard();
  }
}
