package ControllerTests;

import Controller.BoardController;
import DataStructures.Directions;
import Model.BoardGeneratorStub;
import Model.IBoardGenerator;
import Model.IEntityObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PacmanTests {

  private BoardController boardController;

  private int[] findPacman() {
    IEntityObject entityToFind = boardController.getExistingEntityByName("Pacman");
    return new int[] {boardController.getExistingEntityPosition(entityToFind).getX(),
        boardController.getExistingEntityPosition(entityToFind).getY()};
  }

  @Before
  public void initTests() {
    IBoardGenerator boardGeneratorStub = new BoardGeneratorStub();
    boardController = new BoardController(boardGeneratorStub);
  }

  @Test
  public void testPacmanCanRotateUp() {
    boardController.getExistingEntityByName("Pacman").updateCurrentDirection(Directions.Up);
    Assert.assertEquals(Directions.Up,
        boardController.getExistingEntityByName("Pacman").getCurrentDirection());
  }

  @Test
  public void testPacmanCanRotateLeft() {
    boardController.getExistingEntityByName("Pacman").updateCurrentDirection(Directions.Left);
    Assert.assertEquals(Directions.Left,
        boardController.getExistingEntityByName("Pacman").getCurrentDirection());
  }

  @Test
  public void testPacmanCanRotateDown() {
    boardController.getExistingEntityByName("Pacman").updateCurrentDirection(Directions.Down);
    Assert.assertEquals(Directions.Down,
        boardController.getExistingEntityByName("Pacman").getCurrentDirection());
  }

  @Test
  public void testPacmanCanRotateRight() {
    boardController.getExistingEntityByName("Pacman").updateCurrentDirection(Directions.Right);
    Assert.assertEquals(Directions.Right,
        boardController.getExistingEntityByName("Pacman").getCurrentDirection());
  }

  @Test
  public void testPacmanCanMoveUpOnBoard() {
    boardController
        .tryToRotateAndMoveEntity(boardController.getExistingEntityByName("Pacman"), Directions.Up);
    Assert.assertArrayEquals(new int[] {2, 1}, findPacman());
  }

  @Test
  public void testPacmanCanMoveDownOnBoard() {
    boardController
        .tryToRotateAndMoveEntity(boardController.getExistingEntityByName("Pacman"),
            Directions.Down);
    Assert.assertArrayEquals(new int[] {2, 3}, findPacman());
  }

  @Test
  public void testPacmanCanMoveLeftOnBoard() {
    boardController
        .tryToRotateAndMoveEntity(boardController.getExistingEntityByName("Pacman"),
            Directions.Left);
    Assert.assertArrayEquals(new int[] {1, 2}, findPacman());
  }

  @Test
  public void testPacmanCanMoveRightOnBoard() {
    boardController
        .tryToRotateAndMoveEntity(boardController.getExistingEntityByName("Pacman"),
            Directions.Right);
    Assert.assertArrayEquals(new int[] {3, 2}, findPacman());
  }

  @Test
  public void testPacmanCanWrapUpOnBoard() {
    boardController
        .tryToRotateAndMoveEntity(boardController.getExistingEntityByName("Pacman"), Directions.Up);
    boardController
        .tryToRotateAndMoveEntity(boardController.getExistingEntityByName("Pacman"), Directions.Up);
    boardController
        .tryToRotateAndMoveEntity(boardController.getExistingEntityByName("Pacman"), Directions.Up);
    Assert.assertArrayEquals(new int[] {2, 4}, findPacman());
  }

  @Test
  public void testPacmanCanWrapDownOnBoard() {
    boardController
        .tryToRotateAndMoveEntity(boardController.getExistingEntityByName("Pacman"),
            Directions.Down);
    boardController
        .tryToRotateAndMoveEntity(boardController.getExistingEntityByName("Pacman"),
            Directions.Down);
    boardController
        .tryToRotateAndMoveEntity(boardController.getExistingEntityByName("Pacman"),
            Directions.Down);
    Assert.assertArrayEquals(new int[] {2, 0}, findPacman());
  }

  @Test
  public void testPacmanCanWrapLeftOnBoard() {
    boardController
        .tryToRotateAndMoveEntity(boardController.getExistingEntityByName("Pacman"),
            Directions.Left);
    boardController
        .tryToRotateAndMoveEntity(boardController.getExistingEntityByName("Pacman"),
            Directions.Left);
    boardController
        .tryToRotateAndMoveEntity(boardController.getExistingEntityByName("Pacman"),
            Directions.Left);
    Assert.assertArrayEquals(new int[] {4, 2}, findPacman());
  }

  @Test
  public void testPacmanCanWrapRightOnBoard() {
    boardController
        .tryToRotateAndMoveEntity(boardController.getExistingEntityByName("Pacman"),
            Directions.Right);
    boardController
        .tryToRotateAndMoveEntity(boardController.getExistingEntityByName("Pacman"),
            Directions.Right);
    boardController
        .tryToRotateAndMoveEntity(boardController.getExistingEntityByName("Pacman"),
            Directions.Right);
    Assert.assertArrayEquals(new int[] {0, 2}, findPacman());
  }
}