package ControllerTests;

import Controller.BoardController;
import Controller.EnemyController;
import Controller.IBoardGenerator;
import Model.EntityObjects.Ghost;
import Model.EntityObjects.IEntityObject;
import Model.EntityObjects.Pacman;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EnemyControllerTests {

  IBoardGenerator boardGeneratorStub = new BoardGeneratorStub();
  private BoardController boardController = new BoardController(boardGeneratorStub);

  @Before
  public void initializeBoard(){
    boardController = new BoardController(boardGeneratorStub);
    boardController.createEntity("Pacman", boardController.getBoardWidth() / 2, boardController.getBoardHeight() / 2, true);
    boardController.createEntity("Ghost1", 0, 0, false);
    boardController.createEntity("Ghost2", boardController.getBoardWidth() - 1, boardController.getBoardHeight() - 1, false);
  }

  @Test
  public void enemyCanTrackAndMoveToPacman() {
    EnemyController enemyController = new EnemyController();
    IEntityObject ghost = boardController.getExistingEntityByName("Ghost1");
    IEntityObject pacman = boardController.getExistingEntityByName("Pacman");
    enemyController.moveEnemy(boardController, pacman, ghost);
    enemyController.moveEnemy(boardController, pacman, ghost);
    enemyController.moveEnemy(boardController, pacman, ghost);
    enemyController.moveEnemy(boardController, pacman, ghost);
    Assert.assertEquals(1, boardController.getEntityScore(ghost));
  }
}
