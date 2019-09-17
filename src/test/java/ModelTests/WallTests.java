package ModelTests;

import Controller.BoardController;
import DataStructures.Point;
import DataStructures.Directions;
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
    testMaster.tryToRotateAndMoveEntity(testMaster.getExistingEntityByName("Pacman"), Directions.Down);
    testMaster.tryToRotateAndMoveEntity(testMaster.getExistingEntityByName("Pacman"), Directions.Down);
    testMaster.tryToRotateAndMoveEntity(testMaster.getExistingEntityByName("Pacman"), Directions.Left);
    Assert.assertEquals("^", testMaster.getObjectRepresentationAtPosition(new Point(2, 4)));
  }
}
