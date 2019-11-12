package world;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BoardGeneratorTests {

  private IBoardGenerator boardGenerator;
  private Board board;

  @Before
  public void initializeBoard() {
    boardGenerator = new BoardGenerator();
    board = new Board(boardGenerator);
  }

  @Test
  public void correctScoreIsGeneratedOnNewBoard() {
    int boardHeight = board.getBoardHeight();
    int boardWidth = board.getBoardWidth();
    int wallAmount = 0;
    for (int i = 0; i < boardHeight; i++) {
      for (int j = 0; j < boardWidth; j++) {
        if (board.getObjectRepresentationAtPosition(new Point(i, j)).equals("=")) {
          wallAmount++;
        }
      }
    }
    int actual = boardHeight * boardWidth - wallAmount;
    Assert.assertEquals(boardGenerator.scoreAmount(), actual);
  }
}
