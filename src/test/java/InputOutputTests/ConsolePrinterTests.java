package InputOutputTests;

import GameLogicTests.BoardController;
import GameLogicTests.Directions;
import InputOutput.ConsoleOutput;
import org.junit.Test;

public class ConsolePrinterTests {

  private ConsoleOutput consolePrinter;
  private BoardController testMaster;

  @Test
  public void printerPrintsCorrectly() {
    testMaster = new BoardController(5, 5);
    consolePrinter = new ConsoleOutput(testMaster);

    consolePrinter.printBoard();
    testMaster.attemptToRotateAndMoveEntity(testMaster.getExistingEntityByName("Pacman"), Directions.Up);
    consolePrinter.printBoard();
    testMaster.attemptToRotateAndMoveEntity(testMaster.getExistingEntityByName("Pacman"), Directions.Up);
    consolePrinter.printBoard();
    testMaster.attemptToRotateAndMoveEntity(testMaster.getExistingEntityByName("Pacman"), Directions.Up);
    consolePrinter.printBoard();
  }
}
