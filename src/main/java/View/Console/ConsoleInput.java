package View.Console;

import View.IViewInput;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInput implements IViewInput {

  private InputStreamReader fileInputStream = new InputStreamReader(System.in);
  private BufferedReader bufferedReader = new BufferedReader(fileInputStream);
  private ConsoleInputValidation validationChecker = new ConsoleInputValidation();

  @Override
  public int getUserInput() throws IOException {
    if (bufferedReader.ready()) {
      int currentInput = bufferedReader.read();
      if (validationChecker.checkInput(currentInput)) {
        return currentInput;
      } else {
        return 0;
      }
    }
    return 0;
  }
}
