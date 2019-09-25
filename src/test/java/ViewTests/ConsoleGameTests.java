package ViewTests;

import static Utilities.Constants.S_KEY;
import static Utilities.Constants.W_KEY;

import View.IGame;

import org.junit.Assert;
import org.junit.Test;

public class ConsoleGameTests {

    @Test
    public void doesWinConditionDetectPacmanDying() {
        IGame consoleGame = new ConsoleGameStub();
        consoleGame.setupGame();
        consoleGame.runGame(W_KEY);
        Assert.assertFalse(consoleGame.isPacmanAliveOrDotsUneaten());
    }

    @Test
    public void doPacmanAndGhostMoveOnEachTick() {
        IGame consoleGame = new ConsoleGameStub();
        consoleGame.setupGame();
        consoleGame.runGame(S_KEY);
        Assert.assertTrue(consoleGame.isPacmanAliveOrDotsUneaten());
        consoleGame.runGame(W_KEY);
        Assert.assertFalse(consoleGame.isPacmanAliveOrDotsUneaten());
    }
}
