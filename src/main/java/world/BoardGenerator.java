package world;

import datastructures.QuadruplyLinkedList;
import java.util.Random;
import gameobjects.IGameObject;
import gameobjects.Wall;

public class BoardGenerator implements IBoardGenerator {

  // it's strange that BoardGenerator keeps a copy of the last board it generated
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

  // Rename: maximumPossibleScore? numberOfDots?
  // It's unclear that this returns the score of the previously generated board -
  // I think this should be a method on the Board class.
  @Override
  public int scoreAmount() {
    int wallAmount = 0;
    for (int i = 0; i < gameBoard.getHeight(); i++) {
      for (int j = 0; j < gameBoard.getWidth(); j++) {
        IGameObject currentObject = (IGameObject) gameBoard.getValue(new Point(i, j));
        if (!currentObject.isEdible()) {
          wallAmount++;
        }
      }
    }
    return gameBoard.getHeight() * gameBoard.getWidth() - wallAmount;
  }
}
