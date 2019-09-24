package ViewTests;

import static Utilities.Constants.W_KEY;

import View.IGame;
import org.junit.Assert;
import org.junit.Test;

public class ConsoleGameTests {

  @Test
  public void singleTickMovesBothPlayerAndGhost() {
    IGame consoleGame = new ConsoleGameStub();
    consoleGame.setupGame();
    consoleGame.runGame(W_KEY);
    //Assert.assertEquals("V", consoleGame);
  }
}
