package View;

import static Utilities.Constants.GHOST_AMOUNT;
import static Utilities.Constants.TICK_SPEED;
import static Utilities.Constants.UP_INPUT;


import Controller.BoardController;
import Controller.BoardGenerator;
import Controller.EnemyController;
import Controller.IBoardGenerator;
import Controller.IEnemyController;
import Model.EntityObjects.Ghost;
import Model.EntityObjects.Pacman;
import View.Console.ConsoleCleanUp;
import View.Console.ConsoleInputAdapter;
import java.util.ArrayList;

public class Game {

    private final IGameInput consoleInput;
    private final IGameOutput consoleOutput;
    private final ConsoleInputAdapter consoleInputAdapter = new ConsoleInputAdapter();
    private final IBoardGenerator boardGenerator = new BoardGenerator();
    private final BoardController boardController = new BoardController(boardGenerator);
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

        pacman = (Pacman) boardController.getExistingEntityByName("Pacman");
        ghosts = new ArrayList<>();

        int i = 1;
        while (boardController.getExistingEntityByName("Ghost" + i) != null) {
            ghosts.add((Ghost) boardController.getExistingEntityByName("Ghost" + i));
            i++;
        }

        enemyController = new EnemyController();
        pacmanScoreToWin = boardGenerator.scoreAmount() - (ghosts.size());
    }

    public boolean isPacmanAliveOrDotsUneaten() {
        boolean haveAnyGhostsEatenPacman = ghosts.stream().anyMatch(x -> x.getCurrentScore() >= 1);
        return boardController.getEntityScore(pacman) < pacmanScoreToWin && !haveAnyGhostsEatenPacman;
    }

    public void runGame(int currentLevelIteration) {
        setupGame();
        int userInputAsByte = UP_INPUT;
        int rawInput;
        while (isPacmanAliveOrDotsUneaten()) {
            rawInput = consoleInput.getUserInput();

            if (rawInput != 0) {
                userInputAsByte = rawInput;
            }

            boardController.tryToRotateAndMoveEntity(pacman, consoleInputAdapter.translateInputToGameActions(userInputAsByte));
            boardController.alternatePacmanMouth(pacman);

            for (Ghost ghost : ghosts) {
                enemyController.moveEnemy(boardController, pacman, ghost);
            }

            consoleOutput.printBoard(boardController);
            consoleOutput.printScore(boardController.getEntityScore(pacman), currentLevelIteration);

            try {
                Thread.sleep(TICK_SPEED);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        endGame(boardController.getEntityScore(pacman) >= pacmanScoreToWin, currentLevelIteration);
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
