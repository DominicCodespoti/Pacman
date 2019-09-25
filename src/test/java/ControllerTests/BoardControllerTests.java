package ControllerTests;

import Controller.BoardController;
import Controller.IBoardGenerator;
import Model.Directions;
import Model.EntityObjects.IEntityObject;
import Model.Point;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BoardControllerTests {

  private BoardController boardController;

  @Before
  public void initializeBoard() {
    IBoardGenerator boardGeneratorStub = new BoardGeneratorStub();
    boardController = new BoardController(boardGeneratorStub);
    boardController
        .createEntity("Pacman", boardController.getBoardWidth() / 2, boardController.getBoardHeight() / 2, true);
    boardController.createEntity("Ghost1", 0, 0, false);
    boardController
        .createEntity("Ghost2", boardController.getBoardWidth() - 1, boardController.getBoardHeight() - 1, false);
  }

  private int[] findPacman() {
    IEntityObject entityToFind = boardController.getExistingEntityByName("Pacman");
    return new int[] {boardController.getExistingEntityPosition(entityToFind).getX(),
        boardController.getExistingEntityPosition(entityToFind).getY()};
  }

  @Test
  public void testPacmanCanRotateUp() {
    boardController.getExistingEntityByName("Pacman").updateCurrentDirection(Directions.Up);
    Assert.assertEquals(Directions.Up, boardController.getExistingEntityByName("Pacman").getCurrentDirection());
  }

  @Test
  public void testPacmanCanRotateLeft() {
    boardController.getExistingEntityByName("Pacman").updateCurrentDirection(Directions.Left);
    Assert.assertEquals(Directions.Left, boardController.getExistingEntityByName("Pacman").getCurrentDirection());
  }

  @Test
  public void testPacmanCanRotateDown() {
    boardController.getExistingEntityByName("Pacman").updateCurrentDirection(Directions.Down);
    Assert.assertEquals(Directions.Down, boardController.getExistingEntityByName("Pacman").getCurrentDirection());
  }

  @Test
  public void testPacmanCanRotateRight() {
    boardController.getExistingEntityByName("Pacman").updateCurrentDirection(Directions.Right);
    Assert.assertEquals(Directions.Right, boardController.getExistingEntityByName("Pacman").getCurrentDirection());
  }

  @Test
  public void testPacmanCanMoveUpOnBoard() {
    boardController.tryToRotateAndMoveEntity(boardController.getExistingEntityByName("Pacman"), Directions.Up);
    Assert.assertArrayEquals(new int[] {2, 1}, findPacman());
  }

  @Test
  public void testPacmanCanMoveDownOnBoard() {
    boardController.tryToRotateAndMoveEntity(boardController.getExistingEntityByName("Pacman"), Directions.Down);
    Assert.assertArrayEquals(new int[] {2, 3}, findPacman());
  }

  @Test
  public void testPacmanCanMoveLeftOnBoard() {
    boardController.tryToRotateAndMoveEntity(boardController.getExistingEntityByName("Pacman"), Directions.Left);
    Assert.assertArrayEquals(new int[] {1, 2}, findPacman());
  }

  @Test
  public void testPacmanCanMoveRightOnBoard() {
    boardController.tryToRotateAndMoveEntity(boardController.getExistingEntityByName("Pacman"), Directions.Right);
    Assert.assertArrayEquals(new int[] {3, 2}, findPacman());
  }

  @Test
  public void testPacmanCanWrapUpOnBoard() {
    boardController.tryToRotateAndMoveEntity(boardController.getExistingEntityByName("Pacman"), Directions.Up);
    boardController.tryToRotateAndMoveEntity(boardController.getExistingEntityByName("Pacman"), Directions.Up);
    boardController.tryToRotateAndMoveEntity(boardController.getExistingEntityByName("Pacman"), Directions.Up);
    Assert.assertArrayEquals(new int[] {2, 4}, findPacman());
  }

  @Test
  public void testPacmanCanWrapDownOnBoard() {
    boardController.tryToRotateAndMoveEntity(boardController.getExistingEntityByName("Pacman"), Directions.Down);
    boardController.tryToRotateAndMoveEntity(boardController.getExistingEntityByName("Pacman"), Directions.Down);
    boardController.tryToRotateAndMoveEntity(boardController.getExistingEntityByName("Pacman"), Directions.Down);
    Assert.assertArrayEquals(new int[] {2, 0}, findPacman());
  }

  @Test
  public void testPacmanCanWrapLeftOnBoard() {
    boardController.tryToRotateAndMoveEntity(boardController.getExistingEntityByName("Pacman"), Directions.Left);
    boardController.tryToRotateAndMoveEntity(boardController.getExistingEntityByName("Pacman"), Directions.Left);
    boardController.tryToRotateAndMoveEntity(boardController.getExistingEntityByName("Pacman"), Directions.Left);
    Assert.assertArrayEquals(new int[] {4, 2}, findPacman());
  }

  @Test
  public void testPacmanCanWrapRightOnBoard() {
    boardController.tryToRotateAndMoveEntity(boardController.getExistingEntityByName("Pacman"), Directions.Right);
    boardController.tryToRotateAndMoveEntity(boardController.getExistingEntityByName("Pacman"), Directions.Right);
    boardController.tryToRotateAndMoveEntity(boardController.getExistingEntityByName("Pacman"), Directions.Right);
    Assert.assertArrayEquals(new int[] {0, 2}, findPacman());
  }

  @Test
  public void dotBecomesSpaceWhenPacmanMovesOverIt() {
    boardController.tryToRotateAndMoveEntity(boardController.getExistingEntityByName("Pacman"), Directions.Up);
    boardController.tryToRotateAndMoveEntity(boardController.getExistingEntityByName("Pacman"), Directions.Up);
    Assert.assertEquals(" ", boardController.getObjectRepresentationAtPosition(new Point(2, 2)));
  }

  @Test
  public void pacmanDoesNotRotateAndContinuesMovingWhenRotatingIntoWall() {
    boardController.tryToRotateAndMoveEntity(boardController.getExistingEntityByName("Pacman"), Directions.Down);
    boardController.tryToRotateAndMoveEntity(boardController.getExistingEntityByName("Pacman"), Directions.Down);
    boardController.tryToRotateAndMoveEntity(boardController.getExistingEntityByName("Pacman"), Directions.Left);
    Assert.assertEquals("^", boardController.getObjectRepresentationAtPosition(new Point(2, 0)));
  }

  @Test
  public void pacmanDoesNotMoveThroughWallsWhenWalkingIntoThem() {
    boardController.tryToRotateAndMoveEntity(boardController.getExistingEntityByName("Pacman"), Directions.Left);
    boardController.tryToRotateAndMoveEntity(boardController.getExistingEntityByName("Pacman"), Directions.Down);
    boardController.tryToRotateAndMoveEntity(boardController.getExistingEntityByName("Pacman"), Directions.Down);
    boardController.tryToRotateAndMoveEntity(boardController.getExistingEntityByName("Pacman"), Directions.Down);
    Assert.assertEquals("^", boardController.getObjectRepresentationAtPosition(new Point(1, 3)));
  }
}
