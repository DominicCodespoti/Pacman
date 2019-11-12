package utilities;

import world.Direction;
import world.Point;
import org.junit.Assert;
import org.junit.Test;

public class DistanceCalculatorTests {

  @Test
  public void whenTargetIsUpDirectionReturnedFromCalculatorIsUp() {
    Assert
        .assertEquals(Direction.Up, DistanceCalculator.findFastestDirection(new Point(2, 1), new Point(2, 2)));
  }

  @Test
  public void whenTargetIsDownDirectionReturnedFromCalculatorIsDown() {
    Assert.assertEquals(Direction.Down,
        DistanceCalculator.findFastestDirection(new Point(2, 3), new Point(2, 2)));
  }

  @Test
  public void whenTargetIsRightDirectionReturnedFromCalculatorIsRight() {
    Assert.assertEquals(Direction.Right,
        DistanceCalculator.findFastestDirection(new Point(3, 2), new Point(2, 2)));
  }

  @Test
  public void whenTargetIsLeftDirectionReturnedFromCalculatorIsLeft() {
    Assert.assertEquals(Direction.Left,
        DistanceCalculator.findFastestDirection(new Point(1, 2), new Point(2, 2)));
  }
}
