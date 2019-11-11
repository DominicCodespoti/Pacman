package controllertests;

import controller.Board;
import controller.IBoardGenerator;
import model.Direction;
import model.entityobjects.IEntityObject;
import model.entityobjects.Pacman;
import model.Point;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PacmanControllerTests {

  private Board boardController;
  private Pacman pacman;
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
    boardController = new Board(boardGeneratorStub);
    pacman = boardController.createPacman("Pacman", MIDDLE_MIDDLE);
  }

  private Point findPacman() {
    IEntityObject entityToFind = boardController.getExistingEntityByName("Pacman");
    return new Point(boardController.getPosition(entityToFind).getX(),
        boardController.getPosition(entityToFind).getY());
  }

  private void selectPacmanStartingPoint(Point startPoint) {
    IBoardGenerator boardGeneratorStub = new BoardGeneratorStub();
    boardController = new Board(boardGeneratorStub);
    pacman = boardController.createPacman("Pacman", startPoint);
  }

  @Test
  public void pacmanCanRotateUp() {
    pacman.updateCurrentDirection(Direction.Up);
    Assert.assertEquals(Direction.Up, boardController.getExistingEntityByName("Pacman").getCurrentDirection());
  }

  @Test
  public void pacmanCanRotateLeft() {
    pacman.updateCurrentDirection(Direction.Left);
    Assert.assertEquals(Direction.Left, boardController.getExistingEntityByName("Pacman").getCurrentDirection());
  }

  @Test
  public void pacmanCanRotateDown() {
    pacman.updateCurrentDirection(Direction.Down);
    Assert.assertEquals(Direction.Down, boardController.getExistingEntityByName("Pacman").getCurrentDirection());
  }

  @Test
  public void pacmanCanRotateRight() {
    pacman.updateCurrentDirection(Direction.Right);
    Assert.assertEquals(Direction.Right, boardController.getExistingEntityByName("Pacman").getCurrentDirection());
  }

  @Test
  public void pacmanCanMoveUpOnBoard() {
    pacman.move(Direction.Up, boardController);
    Assert.assertEquals(new Point(2, 1), findPacman());
  }

  @Test
  public void pacmanCanMoveDownOnBoard() {
    pacman.move(Direction.Down, boardController);
    Assert.assertEquals(new Point(2, 3), findPacman());
  }

  @Test
  public void pacmanCanMoveLeftOnBoard() {
    pacman.move(Direction.Left, boardController);
    Assert.assertEquals(new Point(1, 2), findPacman());
  }

  @Test
  public void pacmanCanMoveRightOnBoard() {
    pacman.move(Direction.Right, boardController);
    Assert.assertEquals(new Point(3, 2), findPacman());
  }

  @Test
  public void pacmanCanWrapUpOnBoard() {
    selectPacmanStartingPoint(MIDDLE_TOP_OF_BOARD);
    pacman.move(Direction.Up, boardController);
    Assert.assertEquals(MIDDLE_BOTTOM_OF_BOARD, findPacman());
  }

  @Test
  public void pacmanCanWrapDownOnBoard() {
    selectPacmanStartingPoint(MIDDLE_BOTTOM_OF_BOARD);
    pacman.move(Direction.Down, boardController);
    Assert.assertEquals(MIDDLE_TOP_OF_BOARD, findPacman());
  }

  @Test
  public void pacmanCanWrapLeftOnBoard() {
    selectPacmanStartingPoint(MIDDLE_LEFT_OF_BOARD);
    pacman.move(Direction.Left, boardController);
    Assert.assertEquals(MIDDLE_RIGHT_OF_BOARD, findPacman());
  }

  @Test
  public void pacmanCanWrapRightOnBoard() {
    selectPacmanStartingPoint(MIDDLE_RIGHT_OF_BOARD);
    pacman.move(Direction.Right, boardController);
    Assert.assertEquals(MIDDLE_LEFT_OF_BOARD, findPacman());
  }

  @Test
  public void pacmanAlternatesMouthWhileMoving() {
    selectPacmanStartingPoint(MIDDLE_MIDDLE);
    pacman.move(Direction.Up, boardController);
    Assert.assertEquals("|", boardController.getObjectRepresentationAtPosition(new Point(2, 1)));
  }

  @Test
  public void dotBecomesSpaceWhenPacmanMovesOverIt() {
    pacman.move(Direction.Up, boardController);
    Assert.assertEquals(" ", boardController.getObjectRepresentationAtPosition(MIDDLE_MIDDLE));
  }

  @Test
  public void pacmanDoesNotWalkThroughWalls() {
    selectPacmanStartingPoint(UP_TWO_FROM_WALL);
    pacman.move(Direction.Down, boardController);
    pacman.move(Direction.Down, boardController);
    Assert.assertEquals("|", boardController.getObjectRepresentationAtPosition(UP_ONE_FROM_WALL));
  }

  @Test
  public void pacmanDoesNotRotateIntoWalls() {
    selectPacmanStartingPoint(UP_TWO_FROM_WALL);
    pacman.move(Direction.Down, boardController);
    pacman.move(Direction.Down, boardController);
    pacman.move(Direction.Right, boardController);
    pacman.move(Direction.Down, boardController);
    Assert.assertEquals("<", boardController.getObjectRepresentationAtPosition(new Point(1,3)));
  }
}
