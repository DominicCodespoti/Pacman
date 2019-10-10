package View.Console;

import static Utilities.Constants.TICK_SPEED;

import View.IGameInput;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInput implements IGameInput {

  private final InputStreamReader inputStreamReader = new InputStreamReader(System.in);
  private final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

  public ConsoleInput() {
    Runtime runtime = Runtime.getRuntime();
    runtime.addShutdownHook(new ConsoleCleanUp());
    enterRawTerminalMode();
  }

  @Override
  public int getUserInput() {
    try {
      Thread.sleep(TICK_SPEED);
    } catch (InterruptedException e) {
      System.out.println(e.getMessage());
    }
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
