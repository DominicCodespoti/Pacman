package GameLogicTests;

import DataStructures.Point;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WallTests {
  private BoardController testMaster;

  @Before
  public void initTests() {
    testMaster = new BoardController(5, 5);
  }

  @Test
  public void pacmanDoesNotRotateAndDoesNotMoveIfAttemptingToMoveIntoWall() {
    testMaster.attemptToRotateAndMoveEntity(testMaster.getExistingEntityByName("Pacman"), Directions.Down);
    testMaster.attemptToRotateAndMoveEntity(testMaster.getExistingEntityByName("Pacman"), Directions.Down);
    testMaster.attemptToRotateAndMoveEntity(testMaster.getExistingEntityByName("Pacman"), Directions.Left);
    Assert.assertEquals("^", testMaster.getObjectRepresentationAtPosition(new Point(2, 4)));
  }
}
