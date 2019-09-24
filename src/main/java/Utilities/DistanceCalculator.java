package Utilities;

import Model.Directions;
import Model.Point;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

public class DistanceCalculator { //TODO: SIMPLIFY

  private static int calculatePossibleManhattanDistance(Point entityPositionToTrack, Directions possibleDirection,
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

  public static Directions findDirectionWithClosestPath(Point entityPositionToTrack, Point currentEntityPosition) {
    Map<Directions, Integer> possibleDistances = new HashMap<>();
    possibleDistances.put(Directions.Left,
        calculatePossibleManhattanDistance(entityPositionToTrack, Directions.Left, currentEntityPosition));
    possibleDistances.put(Directions.Right,
        calculatePossibleManhattanDistance(entityPositionToTrack, Directions.Right, currentEntityPosition));
    possibleDistances.put(Directions.Up,
        calculatePossibleManhattanDistance(entityPositionToTrack, Directions.Up, currentEntityPosition));
    possibleDistances.put(Directions.Down,
        calculatePossibleManhattanDistance(entityPositionToTrack, Directions.Down, currentEntityPosition));
    Optional<Entry<Directions, Integer>> maxEntry = possibleDistances.entrySet()
        .stream()
        .min(Comparator.comparing(Map.Entry::getValue));
    return maxEntry.get().getKey();
  }
}
