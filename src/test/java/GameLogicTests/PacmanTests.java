package GameLogicTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PacmanTests {

  private BoardController testMaster;

  private int[] findPacman() {
    IEntityObject entityToFind = testMaster.getExistingEntityByName("Pacman");
    return new int[] {testMaster.getExistingEntityPositionByName(entityToFind).getX(),
        testMaster.getExistingEntityPositionByName(entityToFind).getY()};
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
    testMaster.attemptToMoveEntity(testMaster.getExistingEntityByName("Pacman"));
    Assert.assertArrayEquals(new int[] {2, 1}, findPacman());
  }

  @Test
  public void testPacmanCanMoveDownOnBoard() {
    testMaster.getExistingEntityByName("Pacman").updateCurrentDirection(Directions.Down);
    testMaster.attemptToMoveEntity(testMaster.getExistingEntityByName("Pacman"));
    Assert.assertArrayEquals(new int[] {2, 3}, findPacman());
  }

  @Test
  public void testPacmanCanMoveLeftOnBoard() {
    testMaster.getExistingEntityByName("Pacman").updateCurrentDirection(Directions.Left);
    testMaster.attemptToMoveEntity(testMaster.getExistingEntityByName("Pacman"));
    Assert.assertArrayEquals(new int[] {1, 2}, findPacman());
  }

  @Test
  public void testPacmanCanMoveRightOnBoard() {
    testMaster.getExistingEntityByName("Pacman").updateCurrentDirection(Directions.Right);
    testMaster.attemptToMoveEntity(testMaster.getExistingEntityByName("Pacman"));
    Assert.assertArrayEquals(new int[] {3, 2}, findPacman());
  }

  @Test
  public void testPacmanCanWrapUpOnBoard() {
    testMaster.attemptToMoveEntity(testMaster.getExistingEntityByName("Pacman"));
    testMaster.attemptToMoveEntity(testMaster.getExistingEntityByName("Pacman"));
    testMaster.attemptToMoveEntity(testMaster.getExistingEntityByName("Pacman"));
    Assert.assertArrayEquals(new int[] {2, 4}, findPacman());
  }

  @Test
  public void testPacmanCanWrapDownOnBoard() {
    testMaster.getExistingEntityByName("Pacman").updateCurrentDirection(Directions.Down);
    testMaster.attemptToMoveEntity(testMaster.getExistingEntityByName("Pacman"));
    testMaster.attemptToMoveEntity(testMaster.getExistingEntityByName("Pacman"));
    testMaster.attemptToMoveEntity(testMaster.getExistingEntityByName("Pacman"));
    Assert.assertArrayEquals(new int[] {2, 0}, findPacman());
  }

  @Test
  public void testPacmanCanWrapLeftOnBoard() {
    testMaster.getExistingEntityByName("Pacman").updateCurrentDirection(Directions.Left);
    testMaster.attemptToMoveEntity(testMaster.getExistingEntityByName("Pacman"));
    testMaster.attemptToMoveEntity(testMaster.getExistingEntityByName("Pacman"));
    testMaster.attemptToMoveEntity(testMaster.getExistingEntityByName("Pacman"));
    Assert.assertArrayEquals(new int[] {4, 2}, findPacman());
  }

  @Test
  public void testPacmanCanWrapRightOnBoard() {
    testMaster.getExistingEntityByName("Pacman").updateCurrentDirection(Directions.Right);
    testMaster.attemptToMoveEntity(testMaster.getExistingEntityByName("Pacman"));
    testMaster.attemptToMoveEntity(testMaster.getExistingEntityByName("Pacman"));
    testMaster.attemptToMoveEntity(testMaster.getExistingEntityByName("Pacman"));
    Assert.assertArrayEquals(new int[] {0, 2}, findPacman());
  }
}
