package Controller;

import Model.Point;
import DataStructures.QuadruplyLinkedList;
import Model.GameObjects.Wall;
import java.util.Random;

public class BoardGenerator implements IBoardGenerator {

  private QuadruplyLinkedList gameBoard;

  @Override
  public QuadruplyLinkedList generateBoard() {
    Random randomNumberGenerator = new Random();

    int boardWidth = randomNumberGenerator.nextInt(5) + 5;
    int boardHeight = randomNumberGenerator.nextInt(5) + 5;
    gameBoard = new QuadruplyLinkedList(boardWidth, boardHeight);

    for (int i = 0; i < boardHeight; i++) {
      for (int j = 0; j < boardWidth; j++) {
        if (randomNumberGenerator.nextInt(10) == randomNumberGenerator.nextInt(10)) {
          if (i != boardWidth / 2 && j != boardHeight / 2) {
            gameBoard.setValue(new Point(i, j), new Wall());
          }
        }
      }
    }
    return gameBoard;
  }

  @Override
  public int scoreAmount() {
    int gameBoardScore = 0;
    for (int i = 0; i < gameBoard.getHeight(); i++) {
      for (int j = 0; j < gameBoard.getWidth(); j++) {
        if (gameBoard.getValue(new Point(i, j)).isEdible()) {
          gameBoardScore++;
        }
      }
    }
    return gameBoardScore;
  }
}