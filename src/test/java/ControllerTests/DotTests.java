package ControllerTests;

import Controller.BoardController;
import DataStructures.Directions;
import DataStructures.Point;
import Model.BoardGeneratorStub;
import Model.IBoardGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DotTests {

  private BoardController boardController;

  @Before
  public void initTests() {
    IBoardGenerator boardGeneratorStub = new BoardGeneratorStub();
    boardController = new BoardController(boardGeneratorStub);
  }

  @Test
  public void dotBecomesSpaceWhenPacmanMovesOverIt() {
    boardController
        .tryToRotateAndMoveEntity(boardController.getExistingEntityByName("Pacman"), Directions.Up);
    boardController
        .tryToRotateAndMoveEntity(boardController.getExistingEntityByName("Pacman"), Directions.Up);
    Assert.assertEquals(" ", boardController.getObjectRepresentationAtPosition(new Point(2, 2)));
  }
}
