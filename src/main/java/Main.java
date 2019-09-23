import View.Console.ConsoleGame;
import View.Console.ConsoleInput;
import View.Console.ConsoleOutput;
import View.IGame;
import View.IGameInput;
import View.IGameOutput;

public class Main {

  public static void main(String[] args) {
    IGameInput iGameInput = new ConsoleInput();
    IGameOutput iGameOutput = new ConsoleOutput();
    IGame chosenImplementation = new ConsoleGame(iGameInput, iGameOutput);
    chosenImplementation.runGame(1);
  }
}
