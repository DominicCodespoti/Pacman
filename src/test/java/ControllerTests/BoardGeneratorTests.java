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

  private Board boardController;
  private PacmanController pacmanController;
  private final Point MIDDLE_MIDDLE = new Point(2, 2);

  @Before
  public void initializeBoard() {
    IBoardGenerator boardGeneratorStub = new BoardGeneratorStub();
    boardController = new Board(boardGeneratorStub);
    Pacman pacman = (Pacman) boardController.createEntity("Pacman", "Pacman", MIDDLE_MIDDLE);
    pacmanController = new PacmanController(boardController, pacman);
  }

  @Test
  public void wallsDoNotGenerateOnPacman() { //TODO: No stub needed, test makes no sense stubbed
    boardController = new Board(new BoardGenerator());
    Assert.assertEquals("V", boardController.getObjectRepresentationAtPosition(MIDDLE_MIDDLE));
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
