package view.console;

import model.Direction;
import view.IGameInput;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInput implements IGameInput {

  private static final int LEFT_INPUT = 97;
  private static final int RIGHT_INPUT = 100;
  private static final int DOWN_INPUT = 115;
  private static final int UP_INPUT = 119;
  private final InputStreamReader inputStreamReader = new InputStreamReader(System.in);
  private final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

  public ConsoleInput() {
    Runtime runtime = Runtime.getRuntime();
    runtime.addShutdownHook(new ConsoleCleanUp());
    enterRawTerminalMode();
  }

  @Override
  public Direction getUserInput(Direction oldDirection) {
    pause();
    try {
      Direction selectedDirection = getDirections();
      if (selectedDirection != null) {
        return selectedDirection;
      }
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
    return oldDirection;
  }

  private Direction getDirections() throws IOException {
    if (bufferedReader.ready()) {
      switch (bufferedReader.read()) {
        case LEFT_INPUT:
          return Direction.Left;

        case RIGHT_INPUT:
          return Direction.Right;

        case DOWN_INPUT:
          return Direction.Down;

        case UP_INPUT:
          return Direction.Up;
        case 3:
          System.exit(0);
      }
    }
    return null;
  }

  private void pause() {
    try {
      int TICK_SPEED = 1000;
      Thread.sleep(TICK_SPEED);
    } catch (InterruptedException e) {
      System.out.println(e.getMessage());
    }
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
