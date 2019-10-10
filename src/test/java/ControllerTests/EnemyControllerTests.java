package ControllerTests;

import Controller.Board;
import Controller.EnemyController;
import Controller.IBoardGenerator;
import Controller.PacmanController;
import Model.Directions;
import Model.EntityObjects.Ghost;
import Model.EntityObjects.Pacman;
import Model.Point;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EnemyControllerTests {

  private final IBoardGenerator boardGeneratorStub = new BoardGeneratorStub();
  private Ghost ghost;
  private PacmanController pacmanController;
  private EnemyController enemyController;
  private final Point MIDDLE_MIDDLE = new Point(2, 2);

  @Before
  public void initializeBoard() {
    IBoardGenerator boardGeneratorStub = new BoardGeneratorStub();
    Board boardController = new Board(boardGeneratorStub);
    Pacman pacman = (Pacman) boardController.createEntity("Pacman", "Pacman", MIDDLE_MIDDLE);
    pacmanController = new PacmanController(boardController, pacman);
    ghost = (Ghost) boardController.createEntity("Ghost", "Ghost", new Point(2, 3));
    enemyController = new EnemyController(boardController, ghost);
  }

  @Test
  public void enemyCanEatPacman() {
    enemyController.move(Directions.Up);
    Assert.assertEquals(1, ghost.getCurrentScore());
  }
}
