package ControllerTests;

import Controller.BoardController;
import DataStructures.Point;
import Model.BoardGenerator;
import Model.BoardGeneratorStub;
import Model.IBoardGenerator;
import org.junit.Assert;
import org.junit.Test;

public class BoardGeneratorTests {

  private BoardController boardController;

  @Test
  public void wallsDoNotGenerateOnPacman() {
    IBoardGenerator boardGenerator = new BoardGenerator();
    boardController = new BoardController(boardGenerator);
    int boardHeight = boardController.getBoardHeight();
    int boardWidth = boardController.getBoardWidth();
    Point wherePacmanShouldBe = new Point(boardWidth / 2, boardHeight / 2);
    Assert.assertEquals("V", boardController.getObjectRepresentationAtPosition(wherePacmanShouldBe));
  }

  @Test
  public void correctScoreIsGeneratedOnNewBoard() {
    IBoardGenerator boardGenerator = new BoardGeneratorStub();
    boardController = new BoardController(boardGenerator);
    int boardHeight = boardController.getBoardHeight();
    int boardWidth = boardController.getBoardWidth();
    int wallAmount = 0;
    for (int i = 0; i < boardHeight; i++) {
      for (int j = 0; j < boardWidth; j++) {
        if (boardController.getObjectRepresentationAtPosition(new Point(j, i)).equals("=")) {
          wallAmount++;
        }
      }
    }
    Assert.assertEquals(boardGenerator.scoreAmount(), boardHeight * boardWidth - wallAmount);
  }
}
