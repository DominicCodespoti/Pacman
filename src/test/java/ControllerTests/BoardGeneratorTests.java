package ControllerTests;

import Controller.Board;
import Controller.BoardGenerator;
import Controller.IBoardGenerator;
import Controller.PacmanController;
import Model.EntityObjects.Pacman;
import Model.Point;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BoardGeneratorTests {

  private IBoardGenerator boardGenerator;
  private Board boardController;

  @Before
  public void initializeBoard() {
    boardGenerator = new BoardGenerator();
    boardController = new Board(boardGenerator);
  }

  @Test
  public void correctScoreIsGeneratedOnNewBoard() {
    int boardHeight = boardController.getBoardHeight();
    int boardWidth = boardController.getBoardWidth();
    int wallAmount = 0;
    for (int i = 0; i < boardHeight; i++) {
      for (int j = 0; j < boardWidth; j++) {
        if (boardController.getObjectRepresentationAtPosition(new Point(i, j)).equals("=")) {
          wallAmount++;
        }
      }
    }
    int actual = boardHeight * boardWidth - wallAmount;
    Assert.assertEquals(boardGenerator.scoreAmount(), actual);
  }
}
