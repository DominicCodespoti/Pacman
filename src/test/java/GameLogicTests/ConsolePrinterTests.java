package GameLogicTests;

import InputOutput.ConsolePrinter;
import org.junit.Before;
import org.junit.Test;

public class ConsolePrinterTests {
  private ConsolePrinter consolePrinter;
  private BoardController testMaster;

  @Test
  public void printerPrintsCorrectly(){
    testMaster = new BoardController(5, 5);
    consolePrinter = new ConsolePrinter(testMaster);

    consolePrinter.printBoard();
    testMaster.attemptToMoveEntity(testMaster.getExistingEntityByName("Pacman"));
    consolePrinter.printBoard();
    testMaster.attemptToMoveEntity(testMaster.getExistingEntityByName("Pacman"));
    consolePrinter.printBoard();
    testMaster.attemptToMoveEntity(testMaster.getExistingEntityByName("Pacman"));
    consolePrinter.printBoard();
  }
}
