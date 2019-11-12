import world.BoardGenerator;
import view.Game;
import view.console.ConsoleInput;
import view.console.ConsoleOutput;

public class Main {

  public static void main(String[] args) {
    Game game = new Game(new ConsoleInput(), new ConsoleOutput(), new BoardGenerator());
    game.runGame();
  }
}
