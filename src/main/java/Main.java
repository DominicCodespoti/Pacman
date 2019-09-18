import View.Console.ConsoleGame;
import View.IGame;

public class Main {

  public static void main(String[] args) {
    IGame chosenImplementation = new ConsoleGame();
    chosenImplementation.runGame();
  }
}
