import View.Console.ConsoleGame;
import View.Console.ConsoleInput;
import View.Console.ConsoleOutput;
import View.IGame;
import View.IGameInput;
import View.IGameOutput;

public class Main {

  public static void main(String[] args) {
    IGameInput gameInput = new ConsoleInput();
    IGameOutput gameOutput = new ConsoleOutput();
    IGame game = new ConsoleGame(gameInput, gameOutput);
    game.runGame(1);
  }
}
