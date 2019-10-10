package ControllerTests;

import Controller.Board;
import Controller.EnemyController;
import Controller.IBoardGenerator;
import Model.EntityObjects.IEntityObject;
import Model.Point;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EnemyControllerTests {

  private final IBoardGenerator boardGeneratorStub = new BoardGeneratorStub();
  private Board boardController = new Board(boardGeneratorStub);

  @Before
  public void initializeBoard() {
    boardController = new Board(boardGeneratorStub);
    int width = boardController.getBoardWidth();
    int height = boardController.getBoardHeight();
    boardController
        .createEntity("Pacman", width / 2, height / 2, true);
    boardController.createEntity("Ghost1", (width / 2), (height / 2) + 2, false);
  }

  @Test
  public void enemyCanTrackAndMoveTowardsPacman() {
    EnemyController enemyController = new EnemyController();
    IEntityObject ghost = boardController.getExistingEntityByName("Ghost1");
    IEntityObject pacman = boardController.getExistingEntityByName("Pacman");
    enemyController.moveEnemy(boardController, pacman, ghost);
    Assert.assertEquals("G", boardController.getObjectRepresentationAtPosition(new Point(2, 3)));
  }

  @Test
  public void enemyCanTrackAndMoveToPacmanAndEatHim() {
    EnemyController enemyController = new EnemyController();
    IEntityObject ghost = boardController.getExistingEntityByName("Ghost1");
    IEntityObject pacman = boardController.getExistingEntityByName("Pacman");
    enemyController.moveEnemy(boardController, pacman, ghost);
    enemyController.moveEnemy(boardController, pacman, ghost);
    Assert.assertEquals(1, boardController.getEntityScore(ghost));
  }
}
