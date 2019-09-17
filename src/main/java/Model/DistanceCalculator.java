package Model;

import DataStructures.Directions;
import DataStructures.Point;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

public class DistanceCalculator {

  private Point currentyEntityPosition;

  public DistanceCalculator(Point currentyEntityPosition) {
    this.currentyEntityPosition = currentyEntityPosition;
  }

  private int calculatePossibleManhattanDistance(Point entityPositionToTrack,
      Directions possibleDirection) {
    int xDistance;
    int yDistance;
    switch (possibleDirection) {
      case Up:
        xDistance = Math.abs(currentyEntityPosition.getX() - entityPositionToTrack.getX());
        yDistance = Math.abs((currentyEntityPosition.getY() - 1) - entityPositionToTrack.getY());
        return xDistance + yDistance;
      case Down:
        xDistance = Math.abs(currentyEntityPosition.getX() - entityPositionToTrack.getX());
        yDistance = Math.abs((currentyEntityPosition.getY() + 1) - entityPositionToTrack.getY());
        return xDistance + yDistance;
      case Left:
        xDistance = Math.abs((currentyEntityPosition.getX() - 1) - entityPositionToTrack.getX());
        yDistance = Math.abs(currentyEntityPosition.getY() - entityPositionToTrack.getY());
        return xDistance + yDistance;
      case Right:
        xDistance = Math.abs((currentyEntityPosition.getX() + 1) - entityPositionToTrack.getX());
        yDistance = Math.abs(currentyEntityPosition.getY() - entityPositionToTrack.getY());
        return xDistance + yDistance;
    }
    return 0;
  }

  public Directions findDirectionWithClosestPath(Point entityPositionToTrack){
    Map<Directions, Integer> possibleDistances = new HashMap<>();
    possibleDistances.put(Directions.Left, calculatePossibleManhattanDistance(entityPositionToTrack, Directions.Left));
    possibleDistances.put(Directions.Right, calculatePossibleManhattanDistance(entityPositionToTrack, Directions.Right));
    possibleDistances.put(Directions.Up, calculatePossibleManhattanDistance(entityPositionToTrack, Directions.Up));
    possibleDistances.put(Directions.Down, calculatePossibleManhattanDistance(entityPositionToTrack, Directions.Down));
    Optional<Entry<Directions, Integer>> maxEntry = possibleDistances.entrySet()
        .stream()
        .min(Comparator.comparing(Map.Entry::getValue));
    return maxEntry.get().getKey();
  }
}
