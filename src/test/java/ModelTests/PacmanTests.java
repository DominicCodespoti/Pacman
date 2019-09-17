package ModelTests;

import Controller.BoardController;
import DataStructures.Directions;
import Model.IEntityObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PacmanTests {

  private BoardController testMaster;

  private int[] findPacman() {
    IEntityObject entityToFind = testMaster.getExistingEntityByName("Pacman");
    return new int[] {testMaster.getExistingEntityPosition(entityToFind).getX(),
        testMaster.getExistingEntityPosition(entityToFind).getY()};
  }

  @Before
  public void initTests() {
    testMaster = new BoardController(5, 5);
  }

  @Test
  public void testPacmanCanRotateUp() {
    testMaster.getExistingEntityByName("Pacman").updateCurrentDirection(Directions.Up);
    Assert.assertEquals(Directions.Up,
        testMaster.getExistingEntityByName("Pacman").getCurrentDirection());
  }

  @Test
  public void testPacmanCanRotateLeft() {
    testMaster.getExistingEntityByName("Pacman").updateCurrentDirection(Directions.Left);
    Assert.assertEquals(Directions.Left,
        testMaster.getExistingEntityByName("Pacman").getCurrentDirection());
  }

  @Test
  public void testPacmanCanRotateDown() {
    testMaster.getExistingEntityByName("Pacman").updateCurrentDirection(Directions.Down);
    Assert.assertEquals(Directions.Down,
        testMaster.getExistingEntityByName("Pacman").getCurrentDirection());
  }

  @Test
  public void testPacmanCanRotateRight() {
    testMaster.getExistingEntityByName("Pacman").updateCurrentDirection(Directions.Right);
    Assert.assertEquals(Directions.Right,
        testMaster.getExistingEntityByName("Pacman").getCurrentDirection());
  }

  @Test
  public void testPacmanCanMoveUpOnBoard() {
    testMaster.tryToRotateAndMoveEntity(testMaster.getExistingEntityByName("Pacman"), Directions.Up);
    Assert.assertArrayEquals(new int[] {2, 1}, findPacman());
  }

  @Test
  public void testPacmanCanMoveDownOnBoard() {
    testMaster.tryToRotateAndMoveEntity(testMaster.getExistingEntityByName("Pacman"), Directions.Down);
    Assert.assertArrayEquals(new int[] {2, 3}, findPacman());
  }

  @Test
  public void testPacmanCanMoveLeftOnBoard() {
    testMaster.tryToRotateAndMoveEntity(testMaster.getExistingEntityByName("Pacman"), Directions.Left);
    Assert.assertArrayEquals(new int[] {1, 2}, findPacman());
  }

  @Test
  public void testPacmanCanMoveRightOnBoard() {
    testMaster.tryToRotateAndMoveEntity(testMaster.getExistingEntityByName("Pacman"), Directions.Right);
    Assert.assertArrayEquals(new int[] {3, 2}, findPacman());
  }

  @Test
  public void testPacmanCanWrapUpOnBoard() {
    testMaster.tryToRotateAndMoveEntity(testMaster.getExistingEntityByName("Pacman"), Directions.Up);
    testMaster.tryToRotateAndMoveEntity(testMaster.getExistingEntityByName("Pacman"), Directions.Up);
    testMaster.tryToRotateAndMoveEntity(testMaster.getExistingEntityByName("Pacman"), Directions.Up);
    Assert.assertArrayEquals(new int[] {2, 4}, findPacman());
  }

  @Test
  public void testPacmanCanWrapDownOnBoard() {
    testMaster.tryToRotateAndMoveEntity(testMaster.getExistingEntityByName("Pacman"), Directions.Down);
    testMaster.tryToRotateAndMoveEntity(testMaster.getExistingEntityByName("Pacman"), Directions.Down);
    testMaster.tryToRotateAndMoveEntity(testMaster.getExistingEntityByName("Pacman"), Directions.Down);
    Assert.assertArrayEquals(new int[] {2, 0}, findPacman());
  }

  @Test
  public void testPacmanCanWrapLeftOnBoard() {
    testMaster.tryToRotateAndMoveEntity(testMaster.getExistingEntityByName("Pacman"), Directions.Left);
    testMaster.tryToRotateAndMoveEntity(testMaster.getExistingEntityByName("Pacman"), Directions.Left);
    testMaster.tryToRotateAndMoveEntity(testMaster.getExistingEntityByName("Pacman"), Directions.Left);
    Assert.assertArrayEquals(new int[] {4, 2}, findPacman());
  }

  @Test
  public void testPacmanCanWrapRightOnBoard() {
    testMaster.tryToRotateAndMoveEntity(testMaster.getExistingEntityByName("Pacman"), Directions.Right);
    testMaster.tryToRotateAndMoveEntity(testMaster.getExistingEntityByName("Pacman"), Directions.Right);
    testMaster.tryToRotateAndMoveEntity(testMaster.getExistingEntityByName("Pacman"), Directions.Right);
    Assert.assertArrayEquals(new int[] {0, 2}, findPacman());
  }
}
