import controller.BoardGenerator;
import view.Console.ConsoleInput;
import view.Console.ConsoleOutput;
import view.Game;

public class Main {

  public static void main(String[] args) {
    Game game = new Game(new ConsoleInput(), new ConsoleOutput(), new BoardGenerator());
    game.runGame();
  }
}
