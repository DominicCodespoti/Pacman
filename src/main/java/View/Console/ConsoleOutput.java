package View.Console;

import Controller.BoardController;
import DataStructures.Point;
import View.IGameOutput;

public class ConsoleOutput implements IGameOutput {

  private BoardController boardController;

  public ConsoleOutput(BoardController boardController) {
    this.boardController = boardController;
  }

  @Override
  public void printBoard() {
    System.out.print("\033[H\033[2J");
    for (int i = 0; i < boardController.getBoardHeight(); i++) {
      for (int j = 0; j < boardController.getBoardWidth(); j++) {
        System.out.print(boardController.getObjectRepresentationAtPosition(new Point(j, i)));
      }
      System.out.print("\r\n");
    }
    System.out.print("\r\n");
  }

  @Override
  public void printVictory() {
    System.out.print("\033[H\033[2J");
    System.out.println("You win!");
  }

  @Override
  public void printLose() {
    System.out.print("\033[H\033[2J");
    System.out.println("You lose!");
  }
}
