package View.Console;

import View.IGameInput;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInput implements IGameInput {

  private InputStreamReader inputStreamReader = new InputStreamReader(System.in);
  private BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

  @Override
  public int getUserInput() {
    enterRawTerminalMode();
    try {
      if (bufferedReader.ready()) {
        int currentInput = bufferedReader.read();
        if (ConsoleInputValidation.checkInput(currentInput)) {
          return currentInput;
        } else {
          return 0;
        }
      }
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
    return 0;
  }

  private void enterRawTerminalMode() {
    String[] cmd = {"/bin/sh", "-c", "stty raw </dev/tty"};
    try {
      Runtime.getRuntime().exec(cmd).waitFor();
    } catch (InterruptedException | IOException e) {
      System.out.println(e.getMessage());
    }
  }
}
