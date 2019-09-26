package ControllerTests;

import Controller.BoardController;
import Controller.EnemyController;
import Controller.IBoardGenerator;
import Model.Point;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EnemyControllerTests {

  private IBoardGenerator boardGeneratorStub = new BoardGeneratorStub();
  private BoardController boardController = new BoardController(boardGeneratorStub);

  @Before
  public void initializeBoard() {
    boardController = new BoardController(boardGeneratorStub);
    int width = boardController.getBoardWidth();
    int height = boardController.getBoardHeight();
    boardController
        .createEntity("Pacman", width / 2, height / 2, true);
    boardController.createEntity("Ghost1", (width / 2), (height / 2) + 2, false);
  }

  @Test
  public void enemyCanTrackAndMoveTowardsPacman() {
    EnemyController enemyController = new EnemyController();
    enemyController.moveEnemy(boardController, "Pacman", "Ghost1");
    Assert.assertEquals("G", boardController.getObjectRepresentationAtPosition(new Point(2, 3)));
  }

  @Test
  public void enemyCanTrackAndMoveToPacmanAndEatHim() {
    EnemyController enemyController = new EnemyController();
    enemyController.moveEnemy(boardController, "Pacman", "Ghost1");
    enemyController.moveEnemy(boardController, "Pacman", "Ghost1");
    Assert.assertEquals(1, boardController.getEntityScore("Ghost1"));
  }
}
