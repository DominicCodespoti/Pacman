package entities;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import world.Board;
import world.BoardGeneratorStub;
import world.Direction;
import world.IBoardGenerator;
import world.Point;

public class GhostTests {

  private Board board;
  private Ghost ghost;
  private final Point MIDDLE_MIDDLE = new Point(2, 2);
  private final Point MIDDLE_TOP_OF_BOARD = new Point(2, 0);
  private final Point MIDDLE_BOTTOM_OF_BOARD = new Point(2, 4);
  private final Point MIDDLE_LEFT_OF_BOARD = new Point(0, 2);
  private final Point MIDDLE_RIGHT_OF_BOARD = new Point(4, 2);
  private final Point UP_ONE_FROM_WALL = new Point(0, 3);
  private final Point UP_TWO_FROM_WALL = new Point(0, 2);

  @Before
  public void initializeBoard() {
    IBoardGenerator boardGeneratorStub = new BoardGeneratorStub();
    board = new Board(boardGeneratorStub);
    ghost = board.createGhost("Ghost", new Point(2, 2));
  }

  private void selectGhostStartingPoint(Point startPoint) {
    IBoardGenerator boardGeneratorStub = new BoardGeneratorStub();
    board = new Board(boardGeneratorStub);
    ghost = board.createGhost("Ghost", startPoint);
  }

  private Point findGhost() {
    IEntityObject entityToFind = board.getExistingEntityByName("Ghost");
    return new Point(board.getPosition(entityToFind).getX(),
        board.getPosition(entityToFind).getY());
  }

  @Test
  public void enemyCanRotateUp() {
    ghost.move(Direction.Up, board);
    Assert.assertEquals(Direction.Up, board.getExistingEntityByName("Ghost").getCurrentDirection());
  }

  @Test
  public void enemyCanRotateLeft() {
    ghost.move(Direction.Left, board);
    Assert.assertEquals(Direction.Left, board.getExistingEntityByName("Ghost").getCurrentDirection());
  }

  @Test
  public void enemyCanRotateDown() {
    ghost.move(Direction.Down, board);
    Assert.assertEquals(Direction.Down, board.getExistingEntityByName("Ghost").getCurrentDirection());
  }

  @Test
  public void enemyCanRotateRight() {
    ghost.move(Direction.Right, board);
    Assert.assertEquals(Direction.Right, board.getExistingEntityByName("Ghost").getCurrentDirection());
  }

  @Test
  public void enemyCanMoveUpOnBoard() {
    ghost.move(Direction.Up, board);
    Assert.assertEquals(new Point(2, 1), findGhost());
  }

  @Test
  public void enemyCanMoveDownOnBoard() {
    ghost.move(Direction.Down, board);
    Assert.assertEquals(new Point(2, 3), findGhost());
  }

  @Test
  public void enemyCanMoveLeftOnBoard() {
    ghost.move(Direction.Left, board);
    Assert.assertEquals(new Point(1, 2), findGhost());
  }

  @Test
  public void enemyCanMoveRightOnBoard() {
    ghost.move(Direction.Right, board);
    Assert.assertEquals(new Point(3, 2), findGhost());
  }

  @Test
  public void enemyCanWrapUpOnBoard() {
    selectGhostStartingPoint(MIDDLE_TOP_OF_BOARD);
    ghost.move(Direction.Up, board);
    Assert.assertEquals(MIDDLE_BOTTOM_OF_BOARD, findGhost());
  }

  @Test
  public void enemyCanWrapDownOnBoard() {
    selectGhostStartingPoint(MIDDLE_BOTTOM_OF_BOARD);
    ghost.move(Direction.Down, board);
    Assert.assertEquals(MIDDLE_TOP_OF_BOARD, findGhost());
  }

  @Test
  public void enemyCanWrapLeftOnBoard() {
    selectGhostStartingPoint(MIDDLE_LEFT_OF_BOARD);
    ghost.move(Direction.Left, board);
    Assert.assertEquals(MIDDLE_RIGHT_OF_BOARD, findGhost());
  }

  @Test
  public void enemyCanWrapRightOnBoard() {
    selectGhostStartingPoint(MIDDLE_RIGHT_OF_BOARD);
    ghost.move(Direction.Right, board);
    Assert.assertEquals(MIDDLE_LEFT_OF_BOARD, findGhost());
  }

  @Test
  public void dotStaysAsDotWhenEnemyMovesOverIt() {
    ghost.move(Direction.Up, board);
    Assert.assertEquals(".", board.getObjectRepresentationAtPosition(MIDDLE_MIDDLE));
  }

  @Test
  public void enemyDoesNotWalkThroughWalls() {
    selectGhostStartingPoint(UP_TWO_FROM_WALL);
    ghost.move(Direction.Down, board);
    ghost.move(Direction.Down, board);
    Assert.assertEquals("G", board.getObjectRepresentationAtPosition(UP_ONE_FROM_WALL));
  }
}
