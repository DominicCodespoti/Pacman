package ViewTests;

import static Utilities.Constants.S_KEY;
import static Utilities.Constants.W_KEY;

import View.IGame;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ConsoleGameTests {

  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

  @Before
  public void setUpStreams() {
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));
  }

  @Test
  public void doesWinConditionDetectPacmanDying() {
    IGame consoleGame = new ConsoleGameStub();
    consoleGame.setupGame();
    consoleGame.runGame(W_KEY);
    consoleGame.runGame(W_KEY);
    Assert.assertFalse(consoleGame.isPacmanAliveOrDotsUneaten());
  }

  @Test
  public void doPacmanAndGhostMoveOnEachTick() {
    IGame consoleGame = new ConsoleGameStub();
    consoleGame.setupGame();
    consoleGame.runGame(S_KEY);
    consoleGame.runGame(W_KEY);
    Assert.assertTrue(consoleGame.isPacmanAliveOrDotsUneaten());
    consoleGame.runGame(W_KEY);
    Assert.assertFalse(consoleGame.isPacmanAliveOrDotsUneaten());
  }
}
