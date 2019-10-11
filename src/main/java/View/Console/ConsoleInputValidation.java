package View.Console;

import static Utilities.Constants.DOWN_INPUT;
import static Utilities.Constants.EXIT_INPUT;
import static Utilities.Constants.LEFT_INPUT;
import static Utilities.Constants.RIGHT_INPUT;
import static Utilities.Constants.UP_INPUT;

class ConsoleInputValidation {

  static boolean checkInput(int read) {
    return read == EXIT_INPUT ||
        read == UP_INPUT ||
        read == LEFT_INPUT ||
        read == DOWN_INPUT ||
        read == RIGHT_INPUT;
  }
}
