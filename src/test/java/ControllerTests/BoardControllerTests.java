package ControllerTests;

import Controller.BoardController;
import Controller.IBoardGenerator;
import Model.Directions;
import Model.EntityObjects.IEntityObject;
import Model.Point;
import java.util.Map;
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
    Map.Entry<IEntityObject, Point> entityToFind = boardController.getExistingEntitiesEntry("Pacman");
    return new int[] {entityToFind.getValue().getX(), entityToFind.getValue().getY()};
  }

  @Test
  public void testPacmanCanRotateUp() {
    boardController.getExistingEntitiesEntry("Pacman").getKey().updateCurrentDirection(Directions.Up);
    Assert.assertEquals(Directions.Up, boardController.getExistingEntitiesEntry("Pacman").getKey().getCurrentDirection());
  }

  @Test
  public void testPacmanCanRotateLeft() {
    boardController.getExistingEntitiesEntry("Pacman").getKey().updateCurrentDirection(Directions.Left);
    Assert.assertEquals(Directions.Left, boardController.getExistingEntitiesEntry("Pacman").getKey().getCurrentDirection());
  }

  @Test
  public void testPacmanCanRotateDown() {
    boardController.getExistingEntitiesEntry("Pacman").getKey().updateCurrentDirection(Directions.Down);
    Assert.assertEquals(Directions.Down, boardController.getExistingEntitiesEntry("Pacman").getKey().getCurrentDirection());
  }

  @Test
  public void testPacmanCanRotateRight() {
    boardController.getExistingEntitiesEntry("Pacman").getKey().updateCurrentDirection(Directions.Right);
    Assert.assertEquals(Directions.Right, boardController.getExistingEntitiesEntry("Pacman").getKey().getCurrentDirection());
  }

  @Test
  public void testPacmanCanMoveUpOnBoard() {
    boardController.tryToRotateAndMoveEntity("Pacman", Directions.Up);
    Assert.assertEquals(Directions.Up, boardController.getExistingEntitiesEntry("Pacman").getKey().getCurrentDirection());
  }

  @Test
  public void testPacmanCanMoveDownOnBoard() {
    boardController.tryToRotateAndMoveEntity("Pacman", Directions.Down);
    Assert.assertEquals(Directions.Down, boardController.getExistingEntitiesEntry("Pacman").getKey().getCurrentDirection());
  }

  @Test
  public void testPacmanCanMoveLeftOnBoard() {
    boardController.tryToRotateAndMoveEntity("Pacman", Directions.Left);
    Assert.assertEquals(Directions.Left, boardController.getExistingEntitiesEntry("Pacman").getKey().getCurrentDirection());
  }

  @Test
  public void testPacmanCanMoveRightOnBoard() {
    boardController.tryToRotateAndMoveEntity("Pacman", Directions.Right);
    Assert.assertEquals(Directions.Right, boardController.getExistingEntitiesEntry("Pacman").getKey().getCurrentDirection());
  }

  @Test
  public void testPacmanCanWrapUpOnBoard() {
    boardController.tryToRotateAndMoveEntity("Pacman", Directions.Up);
    boardController.tryToRotateAndMoveEntity("Pacman", Directions.Up);
    boardController.tryToRotateAndMoveEntity("Pacman", Directions.Up);
    Assert.assertArrayEquals(new int[] {2, 4}, findPacman());
  }

  @Test
  public void testPacmanCanWrapDownOnBoard() {
    boardController.tryToRotateAndMoveEntity("Pacman", Directions.Down);
    boardController.tryToRotateAndMoveEntity("Pacman", Directions.Down);
    boardController.tryToRotateAndMoveEntity("Pacman", Directions.Down);
    Assert.assertArrayEquals(new int[] {2, 0}, findPacman());
  }

  @Test
  public void testPacmanCanWrapLeftOnBoard() {
    boardController.tryToRotateAndMoveEntity("Pacman", Directions.Left);
    boardController.tryToRotateAndMoveEntity("Pacman", Directions.Left);
    boardController.tryToRotateAndMoveEntity("Pacman", Directions.Left);
    Assert.assertArrayEquals(new int[] {4, 2}, findPacman());
  }

  @Test
  public void testPacmanCanWrapRightOnBoard() {
    boardController.tryToRotateAndMoveEntity("Pacman", Directions.Right);
    boardController.tryToRotateAndMoveEntity("Pacman", Directions.Right);
    boardController.tryToRotateAndMoveEntity("Pacman", Directions.Right);
    Assert.assertArrayEquals(new int[] {0, 2}, findPacman());
  }

  @Test
  public void dotBecomesSpaceWhenPacmanMovesOverIt() {
    boardController.tryToRotateAndMoveEntity("Pacman", Directions.Up);
    boardController.tryToRotateAndMoveEntity("Pacman", Directions.Up);
    Assert.assertEquals(" ", boardController.getObjectRepresentationAtPosition(new Point(2, 2)));
  }

  @Test
  public void pacmanDoesNotRotateAndContinuesMovingWhenRotatingIntoWall() {
    boardController.tryToRotateAndMoveEntity("Pacman", Directions.Down);
    boardController.tryToRotateAndMoveEntity("Pacman", Directions.Down);
    boardController.tryToRotateAndMoveEntity("Pacman", Directions.Left);
    Assert.assertEquals("^", boardController.getObjectRepresentationAtPosition(new Point(2, 0)));
  }

  @Test
  public void pacmanDoesNotMoveThroughWallsWhenWalkingIntoThem() {
    boardController.tryToRotateAndMoveEntity("Pacman", Directions.Left);
    boardController.tryToRotateAndMoveEntity("Pacman", Directions.Down);
    boardController.tryToRotateAndMoveEntity("Pacman", Directions.Down);
    boardController.tryToRotateAndMoveEntity("Pacman", Directions.Down);
    Assert.assertEquals("^", boardController.getObjectRepresentationAtPosition(new Point(1, 3)));
  }
}
