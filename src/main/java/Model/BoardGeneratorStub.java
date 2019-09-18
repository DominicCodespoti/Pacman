package Model;

import DataStructures.Point;
import DataStructures.QuadruplyLinkedList;
import Model.GameObjects.Wall;

public class BoardGeneratorStub implements IBoardGenerator {

  @Override
  public QuadruplyLinkedList generateBoard() {
    QuadruplyLinkedList gameBoard = new QuadruplyLinkedList(5, 5);
    gameBoard.setValue(new Point(0, gameBoard.getWidth() - 1), new Wall());
    gameBoard.setValue(new Point(1, gameBoard.getWidth() - 1), new Wall());
    return gameBoard;
  }

  @Override
  public int scoreAmount() {
    return (5 * 5) - 4;
  }
}
