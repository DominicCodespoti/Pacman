package view.console;

import controller.Board;
import model.Point;
import view.IGameOutput;

public class ConsoleOutput implements IGameOutput {

  @Override
  public void printBoard(Board boardController, int currentScore, int levelAmount) {
    System.out.print("\033[H\033[2J");
    for (int i = 0; i < boardController.getBoardHeight(); i++) {
      for (int j = 0; j < boardController.getBoardWidth(); j++) {
        System.out.print(boardController.getObjectRepresentationAtPosition(new Point(j, i)));
      }
      System.out.print("\r\n");
    }
    System.out.print("\r\n");
    System.out.print("Score: " + currentScore + "\r\n");
    System.out.print("Level: " + levelAmount);
  }

  @Override
  public void printVictory() {
    System.out.print("\033[H\033[2J");
    System.out.print("You win!");
  }

  @Override
  public void printLose() {
    System.out.print("\033[H\033[2J");
    System.out.print("You lose!");
  }
}