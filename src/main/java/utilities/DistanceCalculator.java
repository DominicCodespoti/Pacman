package utilities;

import model.Direction;
import model.Point;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

public class DistanceCalculator {

  private static int calculatePossibleManhattanDistance(Point entityPositionToTrack, Direction possibleDirection,
      Point currentEntityPosition) {
    int xDistance;
    int yDistance;
    switch (possibleDirection) {
      case Up:
        xDistance = Math.abs(currentEntityPosition.getX() - entityPositionToTrack.getX());
        yDistance = Math.abs((currentEntityPosition.getY() - 1) - entityPositionToTrack.getY());
        return xDistance + yDistance;
      case Down:
        xDistance = Math.abs(currentEntityPosition.getX() - entityPositionToTrack.getX());
        yDistance = Math.abs((currentEntityPosition.getY() + 1) - entityPositionToTrack.getY());
        return xDistance + yDistance;
      case Left:
        xDistance = Math.abs((currentEntityPosition.getX() - 1) - entityPositionToTrack.getX());
        yDistance = Math.abs(currentEntityPosition.getY() - entityPositionToTrack.getY());
        return xDistance + yDistance;
      case Right:
        xDistance = Math.abs((currentEntityPosition.getX() + 1) - entityPositionToTrack.getX());
        yDistance = Math.abs(currentEntityPosition.getY() - entityPositionToTrack.getY());
        return xDistance + yDistance;
    }
    return 0;
  }

  public static Direction findFastestDirection(Point entityPositionToTrack, Point currentEntityPosition) {
    Map<Direction, Integer> possibleDistances = new HashMap<>();
    possibleDistances.put(Direction.Left,
        calculatePossibleManhattanDistance(entityPositionToTrack, Direction.Left, currentEntityPosition));
    possibleDistances.put(Direction.Right,
        calculatePossibleManhattanDistance(entityPositionToTrack, Direction.Right, currentEntityPosition));
    possibleDistances.put(Direction.Up,
        calculatePossibleManhattanDistance(entityPositionToTrack, Direction.Up, currentEntityPosition));
    possibleDistances.put(Direction.Down,
        calculatePossibleManhattanDistance(entityPositionToTrack, Direction.Down, currentEntityPosition));
    Optional<Entry<Direction, Integer>> maxEntry = possibleDistances.entrySet().stream()
        .min(Comparator.comparing(Map.Entry::getValue));
    if (maxEntry.isPresent()) {
      return maxEntry.get().getKey();
    }
    return Direction.Up;
  }
}
