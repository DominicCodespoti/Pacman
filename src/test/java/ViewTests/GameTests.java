package ViewTests;

import static Utilities.Constants.S_KEY;
import static Utilities.Constants.W_KEY;

import View.Console.ConsoleInput;
import View.Console.ConsoleOutput;
import View.Console.Game;
import org.junit.Assert;
import org.junit.Test;

public class GameTests {

    @Test
    public void doesWinConditionDetectPacmanDying() {
        Game consoleGame = new GameStub(new ConsoleInput(), new ConsoleOutput());
        consoleGame.setupGame();
        consoleGame.runGame(W_KEY);
        Assert.assertFalse(consoleGame.isPacmanAliveOrDotsUneaten());
    }

    @Test
    public void doPacmanAndGhostMoveOnEachTick() {
        Game consoleGame = new GameStub(new ConsoleInput(), new ConsoleOutput());
        consoleGame.setupGame();
        consoleGame.runGame(S_KEY);
        Assert.assertTrue(consoleGame.isPacmanAliveOrDotsUneaten());
        consoleGame.runGame(W_KEY);
        Assert.assertFalse(consoleGame.isPacmanAliveOrDotsUneaten());
    }
}
