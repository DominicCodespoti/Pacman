package View.Console;

import static Utilities.Constants.DOWN_INPUT;
import static Utilities.Constants.EXIT_INPUT;
import static Utilities.Constants.LEFT_INPUT;
import static Utilities.Constants.RIGHT_INPUT;
import static Utilities.Constants.UP_INPUT;

import Model.Directions;
import View.IGameInput;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInput implements IGameInput {

  private final InputStreamReader inputStreamReader = new InputStreamReader(System.in);
  private final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
  private final int TICK_SPEED;

  public ConsoleInput(int tickSpeed) {
    Runtime runtime = Runtime.getRuntime();
    runtime.addShutdownHook(new ConsoleCleanUp());
    TICK_SPEED = tickSpeed;
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

  @Override
  public Directions translateInputToGameActions(int userInputAsByte) {
    switch (userInputAsByte) {
      case LEFT_INPUT:
        return Directions.Left;

      case RIGHT_INPUT:
        return Directions.Right;

      case DOWN_INPUT:
        return Directions.Down;

      case UP_INPUT:
        return Directions.Up;

      case EXIT_INPUT:
        System.exit(0);
    }
    return null;
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
