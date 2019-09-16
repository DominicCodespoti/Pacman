package GameLogicTests;

import DataStructures.Point;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DotTests {

  private BoardController testMaster;

  @Before
  public void initTests() {
    testMaster = new BoardController(5, 5);
  }

  @Test
  public void dotBecomesSpaceWhenPacmanMovesOverIt() {
    testMaster.attemptToRotateAndMoveEntity(testMaster.getExistingEntityByName("Pacman"), Directions.Up);
    testMaster.attemptToRotateAndMoveEntity(testMaster.getExistingEntityByName("Pacman"), Directions.Up);
    Assert.assertEquals(" ", testMaster.getObjectRepresentationAtPosition(new Point(2, 2)));
  }
}
