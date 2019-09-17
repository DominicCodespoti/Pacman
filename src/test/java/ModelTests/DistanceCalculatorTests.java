package ModelTests;

import DataStructures.Point;
import DataStructures.Directions;
import Model.DistanceCalculator;
import org.junit.Assert;
import org.junit.Test;

public class DistanceCalculatorTests {
  private DistanceCalculator distanceCalculator = new DistanceCalculator(new Point(2,2));

  @Test
  public void whenTargetIsUpDirectionReturnedFromCalculatorIsUp(){
    Assert.assertEquals(Directions.Up, distanceCalculator.findDirectionWithClosestPath(new Point(2,1)));
  }

  @Test
  public void whenTargetIsDownDirectionReturnedFromCalculatorIsDown(){
    Assert.assertEquals(Directions.Down, distanceCalculator.findDirectionWithClosestPath(new Point(2,3)));
  }

  @Test
  public void whenTargetIsRightDirectionReturnedFromCalculatorIsRight(){
    Assert.assertEquals(Directions.Right, distanceCalculator.findDirectionWithClosestPath(new Point(3,2)));
  }

  @Test
  public void whenTargetIsLeftDirectionReturnedFromCalculatorIsLeft(){
    Assert.assertEquals(Directions.Left, distanceCalculator.findDirectionWithClosestPath(new Point(1,2)));
  }
}
