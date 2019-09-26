package View.Console;

import static Utilities.Constants.GHOST_AMOUNT;
import static Utilities.Constants.TICK_SPEED;
import static Utilities.Constants.W_KEY;


import Controller.BoardController;
import Controller.BoardGenerator;
import Controller.EnemyController;
import Controller.IBoardGenerator;
import Controller.IEnemyController;
import Model.EntityObjects.Ghost;
import Model.EntityObjects.Pacman;
import View.IGameInput;
import View.IGameOutput;
import java.util.ArrayList;

public class Game {

    private IGameInput consoleInput;
    private IGameOutput consoleOutput;
    private ConsoleInputAdapter consoleInputAdapter = new ConsoleInputAdapter();
    private IBoardGenerator boardGenerator = new BoardGenerator();
    private BoardController boardController = new BoardController(boardGenerator);
    private IEnemyController enemyController;
    private Pacman pacman;
    private ArrayList<Ghost> ghosts;
    private int pacmanScoreToWin;

    public Game(IGameInput consoleInput, IGameOutput consoleOutput) {
        this.consoleInput = consoleInput;
        this.consoleOutput = consoleOutput;
    }

    public void setupGame() {
        Runtime runtime = Runtime.getRuntime();
        runtime.addShutdownHook(new ConsoleCleanUp());

        boardController.createEntity("Pacman", boardController.getBoardWidth() / 2, boardController.getBoardHeight() / 2, true);

        for (int i = 0; i < GHOST_AMOUNT; i++) {
            boardController.createEntity("Ghost" + (i + 1), i, 0, false);
        }

        pacman = (Pacman) boardController.getExistingEntitiesEntry("Pacman");
        ghosts = new ArrayList<>();

        int i = 1;
        while (boardController.getExistingEntitiesEntry("Ghost" + i) != null) {
            ghosts.add((Ghost) boardController.getExistingEntitiesEntry("Ghost" + i));
            i++;
        }

        enemyController = new EnemyController();
        pacmanScoreToWin = boardGenerator.scoreAmount() - (1 + ghosts.size());
    }

    public boolean isPacmanAliveOrDotsUneaten() {
        boolean haveAnyGhostsEatenPacman = ghosts.stream().anyMatch(x -> x.getCurrentScore() >= 1);
        return boardController.getEntityScore("Pacman") < pacmanScoreToWin && !haveAnyGhostsEatenPacman;
    }

    public void runGame(int currentLevelIteration) {
        setupGame();
        int userInputAsByte = W_KEY, rawInput;
        while (isPacmanAliveOrDotsUneaten()) {
            rawInput = consoleInput.getUserInput();

            if (rawInput != 0) {
                userInputAsByte = rawInput;
            }

            boardController.tryToRotateAndMoveEntity("Pacman", consoleInputAdapter.translateInputToGameActions(userInputAsByte));
            boardController.alternatePacmanMouth("Pacman");

            for (Ghost ghost : ghosts) {
                enemyController.moveEnemy(boardController, "Pacman", ghost.getName());
            }

            consoleOutput.printBoard(boardController);
            consoleOutput.printScore(boardController.getEntityScore("Pacman"), currentLevelIteration);
            try {
                Thread.sleep(TICK_SPEED); //TODO: EXTRACT TO SOMEWHERE
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        endGame(boardController.getEntityScore("Pacman") >= pacmanScoreToWin, currentLevelIteration);
    }

    public void endGame(Boolean playerWin, int currentLevelIteration) {
        if (playerWin) {
            consoleOutput.printVictory();
            Game newGame = new Game(consoleInput, consoleOutput);
            newGame.runGame(++currentLevelIteration);
        } else {
            consoleOutput.printLose();
        }
    }
}
