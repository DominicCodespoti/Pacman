package ControllerTests;

import Controller.Board;
import Controller.IBoardGenerator;
import Controller.PacmanController;
import Model.Directions;
import Model.EntityObjects.IEntityObject;
import Model.EntityObjects.Pacman;
import Model.Point;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PacmanControllerTests {

  private Board boardController;
  private Pacman pacman;
  private PacmanController pacmanController;
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
    pacmanController = new PacmanController(boardController, pacman);
  }

  private Point findPacman() {
    IEntityObject entityToFind = boardController.getExistingEntityByName("Pacman");
    return new Point(boardController.getExistingEntityPosition(entityToFind).getX(),
        boardController.getExistingEntityPosition(entityToFind).getY());
  }

  private void selectPacmanStartingPoint(Point startPoint) {
    IBoardGenerator boardGeneratorStub = new BoardGeneratorStub();
    boardController = new Board(boardGeneratorStub);
    pacman = boardController.createPacman("Pacman", startPoint);
    pacmanController = new PacmanController(boardController, pacman);
  }

  @Test
  public void pacmanCanRotateUp() {
    pacman.updateCurrentDirection(Directions.Up);
    Assert.assertEquals(Directions.Up, boardController.getExistingEntityByName("Pacman").getCurrentDirection());
  }

  @Test
  public void pacmanCanRotateLeft() {
    pacman.updateCurrentDirection(Directions.Left);
    Assert.assertEquals(Directions.Left, boardController.getExistingEntityByName("Pacman").getCurrentDirection());
  }

  @Test
  public void pacmanCanRotateDown() {
    pacman.updateCurrentDirection(Directions.Down);
    Assert.assertEquals(Directions.Down, boardController.getExistingEntityByName("Pacman").getCurrentDirection());
  }

  @Test
  public void pacmanCanRotateRight() {
    pacman.updateCurrentDirection(Directions.Right);
    Assert.assertEquals(Directions.Right, boardController.getExistingEntityByName("Pacman").getCurrentDirection());
  }

  @Test
  public void pacmanCanMoveUpOnBoard() {
    pacmanController.move(Directions.Up);
    Assert.assertEquals(new Point(2, 1), findPacman());
  }

  @Test
  public void pacmanCanMoveDownOnBoard() {
    pacmanController.move(Directions.Down);
    Assert.assertEquals(new Point(2, 3), findPacman());
  }

  @Test
  public void pacmanCanMoveLeftOnBoard() {
    pacmanController.move(Directions.Left);
    Assert.assertEquals(new Point(1, 2), findPacman());
  }

  @Test
  public void pacmanCanMoveRightOnBoard() {
    pacmanController.move(Directions.Right);
    Assert.assertEquals(new Point(3, 2), findPacman());
  }

  @Test
  public void pacmanCanWrapUpOnBoard() {
    selectPacmanStartingPoint(MIDDLE_TOP_OF_BOARD);
    pacmanController.move(Directions.Up);
    Assert.assertEquals(MIDDLE_BOTTOM_OF_BOARD, findPacman());
  }

  @Test
  public void pacmanCanWrapDownOnBoard() {
    selectPacmanStartingPoint(MIDDLE_BOTTOM_OF_BOARD);
    pacmanController.move(Directions.Down);
    Assert.assertEquals(MIDDLE_TOP_OF_BOARD, findPacman());
  }

  @Test
  public void pacmanCanWrapLeftOnBoard() {
    selectPacmanStartingPoint(MIDDLE_LEFT_OF_BOARD);
    pacmanController.move(Directions.Left);
    Assert.assertEquals(MIDDLE_RIGHT_OF_BOARD, findPacman());
  }

  @Test
  public void pacmanCanWrapRightOnBoard() {
    selectPacmanStartingPoint(MIDDLE_RIGHT_OF_BOARD);
    pacmanController.move(Directions.Right);
    Assert.assertEquals(MIDDLE_LEFT_OF_BOARD, findPacman());
  }

  @Test
  public void pacmanAlternatesMouthWhileMoving() {
    selectPacmanStartingPoint(MIDDLE_MIDDLE);
    pacmanController.move(Directions.Up);
    Assert.assertEquals("|", boardController.getObjectRepresentationAtPosition(new Point(2, 1)));
  }

  @Test
  public void dotBecomesSpaceWhenPacmanMovesOverIt() {
    pacmanController.move(Directions.Up);
    Assert.assertEquals(" ", boardController.getObjectRepresentationAtPosition(MIDDLE_MIDDLE));
  }

  @Test
  public void pacmanDoesNotWalkThroughWalls() {
    selectPacmanStartingPoint(UP_TWO_FROM_WALL);
    pacmanController.move(Directions.Down);
    pacmanController.move(Directions.Down);
    Assert.assertEquals("|", boardController.getObjectRepresentationAtPosition(UP_ONE_FROM_WALL));
  }

  @Test
  public void pacmanDoesNotRotateIntoWalls() {
    selectPacmanStartingPoint(UP_TWO_FROM_WALL);
    pacmanController.move(Directions.Down);
    pacmanController.move(Directions.Down);
    pacmanController.move(Directions.Right);
    pacmanController.move(Directions.Down);
    Assert.assertEquals("-", boardController.getObjectRepresentationAtPosition(new Point(2,3)));
  }
}
