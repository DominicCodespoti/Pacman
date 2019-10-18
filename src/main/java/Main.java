import Controller.BoardGenerator;
import View.Console.ConsoleInput;
import View.Console.ConsoleOutput;
import View.Game;

public class Main {

  public static void main(String[] args) {
    Game game = new Game(new ConsoleInput(), new ConsoleOutput(), new BoardGenerator());
    game.runGame();
  }
}
