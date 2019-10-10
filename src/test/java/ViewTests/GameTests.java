package ViewTests;

import View.Console.ConsoleInput;
import View.Console.ConsoleOutput;
import View.Game;
import org.junit.Assert;
import org.junit.Test;

public class GameTests {

  @Test //TODO: Add more and pass stub generator
  public void doesWinConditionDetectPacmanDying() {
    Game consoleGame = new Game(new ConsoleInput(), new ConsoleOutput());
    consoleGame.setupGame();
    consoleGame.runGame(1);
    Assert.assertFalse(consoleGame.isPacmanAliveOrDotsUneaten());
  }
}
