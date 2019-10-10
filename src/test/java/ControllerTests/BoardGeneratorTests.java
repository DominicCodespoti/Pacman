package ControllerTests;

import Controller.Board;
import Controller.IBoardGenerator;
import Model.Point;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BoardGeneratorTests {

  private Board boardController;

  @Before
  public void initializeBoard() {
    IBoardGenerator boardGeneratorStub = new BoardGeneratorStub();
    boardController = new Board(boardGeneratorStub);
    boardController
        .createEntity("Pacman", boardController.getBoardWidth() / 2, boardController.getBoardHeight() / 2, true);
    boardController.createEntity("Ghost1", 0, 0, false);
    boardController
        .createEntity("Ghost2", boardController.getBoardWidth() - 1, boardController.getBoardHeight() - 1, false);
  }

  @Test
  public void wallsDoNotGenerateOnPacman() { //TODO: INTRODUCE BEFORE
    int boardHeight = boardController.getBoardHeight();
    int boardWidth = boardController.getBoardWidth();
    Point wherePacmanShouldBe = new Point(boardWidth / 2, boardHeight / 2);
    Assert.assertEquals("V", boardController.getObjectRepresentationAtPosition(wherePacmanShouldBe));
  }

  @Test
  public void correctScoreIsGeneratedOnNewBoard() {
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
    Assert.assertEquals(new BoardGeneratorStub().scoreAmount(), boardHeight * boardWidth - wallAmount);
  }
}
