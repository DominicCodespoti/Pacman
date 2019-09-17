package ModelTests;

import Controller.BoardController;
import DataStructures.Point;
import DataStructures.Directions;
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
    testMaster.tryToRotateAndMoveEntity(testMaster.getExistingEntityByName("Pacman"), Directions.Up);
    testMaster.tryToRotateAndMoveEntity(testMaster.getExistingEntityByName("Pacman"), Directions.Up);
    Assert.assertEquals(" ", testMaster.getObjectRepresentationAtPosition(new Point(2, 2)));
  }
}
