package InputOutput;

import GameLogicTests.BoardController;

public class ConsoleInterface {
  BoardController boardController;

  public void runGame(){
    boardController = new BoardController(5, 8);
    System.out.println("Welcome to Pacman! Press the 'enter' key to start: ");
  }
}
