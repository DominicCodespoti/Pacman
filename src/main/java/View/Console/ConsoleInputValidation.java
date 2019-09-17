package View.Console;

class ConsoleInputValidation {

  boolean checkInput(int read) {
    return read == 3 ||
        read == 119 ||
        read == 97 ||
        read == 100 ||
        read == 115;
  }
}
