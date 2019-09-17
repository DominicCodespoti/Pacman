package ViewTests;

import Controller.BoardController;
import DataStructures.Directions;
import View.Console.ConsoleOutput;
import org.junit.Test;

public class ConsolePrinterTests {

  private ConsoleOutput consolePrinter;
  private BoardController testMaster;

  @Test
  public void printerPrintsCorrectly() {
    testMaster = new BoardController(5, 5);
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
