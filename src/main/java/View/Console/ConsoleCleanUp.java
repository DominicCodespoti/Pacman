package View.Console;

import java.io.IOException;

public class ConsoleCleanUp extends Thread {
  public void run(){
    String[] cmd = {"/bin/sh", "-c", "stty cooked </dev/tty"};
    try {
      System.out.println("Console is now in cooked mode!");
      Runtime.getRuntime().exec(cmd).waitFor();
    } catch (InterruptedException | IOException e) {
      e.printStackTrace();
    }
  }
}
