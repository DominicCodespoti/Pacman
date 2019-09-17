package View;

import DataStructures.Point;
import Controller.BoardController;

public class ConsoleOutput implements IViewOutput{
  private BoardController boardController;

  public ConsoleOutput (BoardController boardController){
    this.boardController = boardController;
  }

  public void clearScreen(){
    System.out.print("\033[H\033[2J");
  }

  @Override
  public void printBoard(){
    for (int i = 0; i < boardController.getBoardHeight(); i++) {
      for (int j = 0; j < boardController.getBoardWidth(); j++) {
        System.out.print(boardController.getObjectRepresentationAtPosition(new Point(j, i)));
      }
      System.out.print("\r\n");
    }
    System.out.print("\r\n");
  }
}
