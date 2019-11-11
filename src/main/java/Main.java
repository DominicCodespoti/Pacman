import controller.BoardGenerator;
import view.console.ConsoleInput;
import view.console.ConsoleOutput;
import view.Game;

public class Main {

  static void main(String[] args) {
    Game game = new Game(new ConsoleInput(), new ConsoleOutput(), new BoardGenerator());
    game.runGame();
  }
}
