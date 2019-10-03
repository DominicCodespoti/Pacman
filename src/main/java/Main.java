import View.Game;
import View.Console.ConsoleInput;
import View.Console.ConsoleOutput;
import View.IGameInput;
import View.IGameOutput;

public class Main {

  public static void main(String[] args) {
    IGameInput gameInput = new ConsoleInput();
    IGameOutput gameOutput = new ConsoleOutput();
    Game game = new Game(gameInput, gameOutput);
    game.runGame(1);
  }
}
