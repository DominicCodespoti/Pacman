package ViewTests;

import Model.Directions;
import View.IGameInput;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StubInput implements IGameInput {

  private static final int LEFT_INPUT = 97;
  private static final int RIGHT_INPUT = 100;
  private static final int DOWN_INPUT = 115;
  private static final int UP_INPUT = 119;
  private final InputStreamReader inputStreamReader = new InputStreamReader(System.in);
  private final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

  @Override
  public Directions getUserInput(Directions oldDirection) {
    pause();
    try {
      Directions selectedDirection = getDirections();
      if (selectedDirection != null) {
        return selectedDirection;
      }
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
    return oldDirection;
  }

  private Directions getDirections() throws IOException {
    if (bufferedReader.ready()) {
      switch (bufferedReader.read()) {
        case LEFT_INPUT:
          return Directions.Left;

        case RIGHT_INPUT:
          return Directions.Right;

        case DOWN_INPUT:
          return Directions.Down;

        case UP_INPUT:
          return Directions.Up;
        case 3:
          System.exit(0);
      }
    }
    return null;
  }

  private void pause() {
    try {
      int TICK_SPEED = 0;
      Thread.sleep(TICK_SPEED);
    } catch (InterruptedException e) {
      System.out.println(e.getMessage());
    }
  }
}
