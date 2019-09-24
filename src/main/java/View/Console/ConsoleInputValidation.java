package View.Console;

import static Utilities.Constants.A_KEY;
import static Utilities.Constants.D_KEY;
import static Utilities.Constants.EXIT_KEY;
import static Utilities.Constants.S_KEY;
import static Utilities.Constants.W_KEY;

class ConsoleInputValidation {

  static boolean checkInput(int read) {
    return read == EXIT_KEY ||
        read == W_KEY ||
        read == A_KEY ||
        read == S_KEY ||
        read == D_KEY;
  }
}
