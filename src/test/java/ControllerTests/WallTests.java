package ControllerTests;

import Controller.BoardController;
import DataStructures.Point;
import DataStructures.Directions;
import Model.BoardGeneratorStub;
import Model.IBoardGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WallTests {
  private BoardController testMaster;

  @Before
  public void initTests() {
    IBoardGenerator boardGeneratorStub = new BoardGeneratorStub();
    testMaster = new BoardController(boardGeneratorStub);
  }

  @Test
  public void pacmanDoesNotRotateAndDoesNotMoveIfAttemptingToMoveIntoWall() {
    testMaster.tryToRotateAndMoveEntity(testMaster.getExistingEntityByName("Pacman"), Directions.Down);
    testMaster.tryToRotateAndMoveEntity(testMaster.getExistingEntityByName("Pacman"), Directions.Down);
    testMaster.tryToRotateAndMoveEntity(testMaster.getExistingEntityByName("Pacman"), Directions.Left);
    Assert.assertEquals("^", testMaster.getObjectRepresentationAtPosition(new Point(2, 4)));
  }
}
