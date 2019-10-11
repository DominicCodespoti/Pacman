import static Utilities.Constants.TICK_SPEED;


import Controller.BoardGenerator;
import Controller.IBoardGenerator;
import View.Console.ConsoleInput;
import View.Console.ConsoleOutput;
import View.Game;
import View.IGameInput;
import View.IGameOutput;

public class Main {

  public static void main(String[] args) {
    IGameInput gameInput = new ConsoleInput(TICK_SPEED);
    IGameOutput gameOutput = new ConsoleOutput();
    IBoardGenerator boardGenerator = new BoardGenerator();
    Game game = new Game(gameInput, gameOutput, boardGenerator);
    game.runGame(1, 2);
  }
}
