package utilities;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import world.Direction;
import world.Point;

public class DistanceCalculator {

  private static int calculateManhattanDistance(Point targetPosition, Direction direction,
      Point currentPosition) {
    int xDistance;
    int yDistance;
    switch (direction) {
      case Up:
        xDistance = Math.abs(currentPosition.getX() - targetPosition.getX());
        yDistance = Math.abs((currentPosition.getY() - 1) - targetPosition.getY());
        return xDistance + yDistance;
      case Down:
        xDistance = Math.abs(currentPosition.getX() - targetPosition.getX());
        yDistance = Math.abs((currentPosition.getY() + 1) - targetPosition.getY());
        return xDistance + yDistance;
      case Left:
        xDistance = Math.abs((currentPosition.getX() - 1) - targetPosition.getX());
        yDistance = Math.abs(currentPosition.getY() - targetPosition.getY());
        return xDistance + yDistance;
      case Right:
        xDistance = Math.abs((currentPosition.getX() + 1) - targetPosition.getX());
        yDistance = Math.abs(currentPosition.getY() - targetPosition.getY());
        return xDistance + yDistance;
    }
    return 0;
  }

  public static Direction findFastestDirection(Point targetPosition, Point currentPosition) {
    Map<Direction, Integer> possibleDistances = new HashMap<>();
    possibleDistances.put(Direction.Left,
        calculateManhattanDistance(targetPosition, Direction.Left, currentPosition));
    possibleDistances.put(Direction.Right,
        calculateManhattanDistance(targetPosition, Direction.Right, currentPosition));
    possibleDistances.put(Direction.Up,
        calculateManhattanDistance(targetPosition, Direction.Up, currentPosition));
    possibleDistances.put(Direction.Down,
        calculateManhattanDistance(targetPosition, Direction.Down, currentPosition));
    Optional<Entry<Direction, Integer>> maxEntry = possibleDistances.entrySet().stream()
        .min(Comparator.comparing(Map.Entry::getValue));
    if (maxEntry.isPresent()) {
      return maxEntry.get().getKey();
    }
    return Direction.Up;
  }
}
