import View.ConsoleView;
import View.IView;
import java.io.IOException;

public class Main {

  public static void main(String[] args) throws IOException, InterruptedException {
    IView chosenImplementation = new ConsoleView();
    chosenImplementation.runGame();
  }
}
