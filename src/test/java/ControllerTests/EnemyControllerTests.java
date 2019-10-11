package ControllerTests;

import Controller.Board;
import Controller.EnemyController;
import Controller.IBoardGenerator;
import Model.Directions;
import Model.EntityObjects.Ghost;
import Model.EntityObjects.IEntityObject;
import Model.Point;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EnemyControllerTests {

  private Board boardController;
  private Ghost ghost;
  private EnemyController enemyController;
  private final Point MIDDLE_MIDDLE = new Point(2, 2);
  private final Point MIDDLE_TOP_OF_BOARD = new Point(2, 0);
  private final Point MIDDLE_BOTTOM_OF_BOARD = new Point(2, 4);
  private final Point MIDDLE_LEFT_OF_BOARD = new Point(0, 2);
  private final Point MIDDLE_RIGHT_OF_BOARD = new Point(4, 2);
  private Point UP_ONE_FROM_WALL = new Point(0, 3);
  private Point UP_TWO_FROM_WALL = new Point(0, 2);

  @Before
  public void initializeBoard() {
    IBoardGenerator boardGeneratorStub = new BoardGeneratorStub();
    boardController = new Board(boardGeneratorStub);
    ghost = boardController.createGhost("Ghost", new Point(2, 2));
    enemyController = new EnemyController(boardController, ghost);
  }

  private void selectGhostStartingPoint(Point startPoint) {
    IBoardGenerator boardGeneratorStub = new BoardGeneratorStub();
    boardController = new Board(boardGeneratorStub);
    ghost = boardController.createGhost("Ghost", startPoint);
    enemyController = new EnemyController(boardController, ghost);
  }

  private Point findGhost() {
    IEntityObject entityToFind = boardController.getExistingEntityByName("Ghost");
    return new Point(boardController.getExistingEntityPosition(entityToFind).getX(),
        boardController.getExistingEntityPosition(entityToFind).getY());
  }

  @Test
  public void enemyCanRotateUp() {
    ghost.updateCurrentDirection(Directions.Up);
    Assert.assertEquals(Directions.Up, boardController.getExistingEntityByName("Ghost").getCurrentDirection());
  }

  @Test
  public void enemyCanRotateLeft() {
    ghost.updateCurrentDirection(Directions.Left);
    Assert.assertEquals(Directions.Left, boardController.getExistingEntityByName("Ghost").getCurrentDirection());
  }

  @Test
  public void enemyCanRotateDown() {
    ghost.updateCurrentDirection(Directions.Down);
    Assert.assertEquals(Directions.Down, boardController.getExistingEntityByName("Ghost").getCurrentDirection());
  }

  @Test
  public void enemyCanRotateRight() {
    ghost.updateCurrentDirection(Directions.Right);
    Assert.assertEquals(Directions.Right, boardController.getExistingEntityByName("Ghost").getCurrentDirection());
  }

  @Test
  public void enemyCanMoveUpOnBoard() {
    enemyController.move(Directions.Up);
    Assert.assertEquals(new Point(2, 1), findGhost());
  }

  @Test
  public void enemyCanMoveDownOnBoard() {
    enemyController.move(Directions.Down);
    Assert.assertEquals(new Point(2, 3), findGhost());
  }

  @Test
  public void enemyCanMoveLeftOnBoard() {
    enemyController.move(Directions.Left);
    Assert.assertEquals(new Point(1, 2), findGhost());
  }

  @Test
  public void enemyCanMoveRightOnBoard() {
    enemyController.move(Directions.Right);
    Assert.assertEquals(new Point(3, 2), findGhost());
  }

  @Test
  public void enemyCanWrapUpOnBoard() {
    selectGhostStartingPoint(MIDDLE_TOP_OF_BOARD);
    enemyController.move(Directions.Up);
    Assert.assertEquals(MIDDLE_BOTTOM_OF_BOARD, findGhost());
  }

  @Test
  public void enemyCanWrapDownOnBoard() {
    selectGhostStartingPoint(MIDDLE_BOTTOM_OF_BOARD);
    enemyController.move(Directions.Down);
    Assert.assertEquals(MIDDLE_TOP_OF_BOARD, findGhost());
  }

  @Test
  public void enemyCanWrapLeftOnBoard() {
    selectGhostStartingPoint(MIDDLE_LEFT_OF_BOARD);
    enemyController.move(Directions.Left);
    Assert.assertEquals(MIDDLE_RIGHT_OF_BOARD, findGhost());
  }

  @Test
  public void enemyCanWrapRightOnBoard() {
    selectGhostStartingPoint(MIDDLE_RIGHT_OF_BOARD);
    enemyController.move(Directions.Right);
    Assert.assertEquals(MIDDLE_LEFT_OF_BOARD, findGhost());
  }

  @Test
  public void dotStaysAsDotWhenEnemyMovesOverIt() {
    enemyController.move(Directions.Up);
    Assert.assertEquals(".", boardController.getObjectRepresentationAtPosition(MIDDLE_MIDDLE));
  }

  @Test
  public void enemyDoesNotWalkThroughWalls() {
    selectGhostStartingPoint(UP_TWO_FROM_WALL);
    enemyController.move(Directions.Down);
    enemyController.move(Directions.Down);
    Assert.assertEquals("G", boardController.getObjectRepresentationAtPosition(UP_ONE_FROM_WALL));
  }
}
