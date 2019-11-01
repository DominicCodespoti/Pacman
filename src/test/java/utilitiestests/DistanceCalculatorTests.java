package utilitiestests;

import model.Directions;
import model.Point;
import utilities.DistanceCalculator;
import org.junit.Assert;
import org.junit.Test;

public class DistanceCalculatorTests {

  @Test
  public void whenTargetIsUpDirectionReturnedFromCalculatorIsUp() {
    Assert
        .assertEquals(Directions.Up, DistanceCalculator.findDirectionWithClosestPath(new Point(2, 1), new Point(2, 2)));
  }

  @Test
  public void whenTargetIsDownDirectionReturnedFromCalculatorIsDown() {
    Assert.assertEquals(Directions.Down,
        DistanceCalculator.findDirectionWithClosestPath(new Point(2, 3), new Point(2, 2)));
  }

  @Test
  public void whenTargetIsRightDirectionReturnedFromCalculatorIsRight() {
    Assert.assertEquals(Directions.Right,
        DistanceCalculator.findDirectionWithClosestPath(new Point(3, 2), new Point(2, 2)));
  }

  @Test
  public void whenTargetIsLeftDirectionReturnedFromCalculatorIsLeft() {
    Assert.assertEquals(Directions.Left,
        DistanceCalculator.findDirectionWithClosestPath(new Point(1, 2), new Point(2, 2)));
  }
}
