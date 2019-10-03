package ViewTests;

import static Utilities.Constants.DOWN_INPUT;
import static Utilities.Constants.UP_INPUT;

import View.Console.ConsoleInput;
import View.Console.ConsoleOutput;
import View.Game;
import org.junit.Assert;
import org.junit.Test;

public class GameTests {

    @Test
    public void doesWinConditionDetectPacmanDying() {
        Game consoleGame = new GameStub(new ConsoleInput(), new ConsoleOutput());
        consoleGame.setupGame();
        consoleGame.runGame(UP_INPUT);
        Assert.assertFalse(consoleGame.isPacmanAliveOrDotsUneaten());
    }

    @Test
    public void doPacmanAndGhostMoveOnEachTick() {
        Game consoleGame = new GameStub(new ConsoleInput(), new ConsoleOutput());
        consoleGame.setupGame();
        consoleGame.runGame(DOWN_INPUT);
        Assert.assertTrue(consoleGame.isPacmanAliveOrDotsUneaten());
        consoleGame.runGame(UP_INPUT);
        Assert.assertFalse(consoleGame.isPacmanAliveOrDotsUneaten());
    }
}
