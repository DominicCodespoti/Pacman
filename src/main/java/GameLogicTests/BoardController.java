package GameLogicTests;

import DataStructures.MultiLayerLinkedList;
import DataStructures.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class BoardController {

  private MultiLayerLinkedList gameBoard;
  private Map<IEntityObject, Point> currentEntities = new HashMap<>();

  public BoardController(int mapHeight, int mapWidth) {
    gameBoard = new MultiLayerLinkedList(mapWidth, mapHeight);
    Pacman pacman = new Pacman("Pacman");
    gameBoard.setValue(new Point(mapHeight / 2, mapWidth / 2), pacman);
    currentEntities.put(pacman, new Point(mapHeight / 2, mapWidth / 2));
  }

  public Point getExistingEntityPositionByName(IEntityObject entityToMove) {
    Entry test = currentEntities.entrySet().stream()
        .filter(x -> x.getKey().getName().equals(entityToMove.getName())).findFirst().orElse(null);
    return (Point) test.getValue();
  }

  public IEntityObject getExistingEntityByName(String entityName) {
    return currentEntities.keySet().stream()
        .filter(x -> x.getName().equals(entityName))
        .findAny()
        .orElse(null);
  }

  public void attemptToMoveEntity(IEntityObject entityToMove) {
    if (isPathUnblocked(entityToMove)) {
      return;
    }
    movePositionOnBoard(entityToMove);
    attemptToEat(entityToMove);
  }

  public String getObjectRepresentationAtPosition(Point positionToGet){
    return gameBoard.getValue(positionToGet).getString();
  }

  public int getBoardWidth(){
    return gameBoard.GetWidth();
  }

  public int getBoardHeight(){
    return gameBoard.GetHeight();
  }

  private void updateEntityPosition(IEntityObject entityToMove, Point newPosition) {
    Entry test = currentEntities.entrySet().stream()
        .filter(x -> x.getKey().getName().equals(entityToMove.getName())).findFirst().orElse(null);
    test.setValue(newPosition);
  }

  private boolean isPathUnblocked(IEntityObject entityToCheck) {
    Point entityPosition = getExistingEntityPositionByName(entityToCheck);
    Directions entityDirection = getExistingEntityByName(entityToCheck.getName())
        .getCurrentDirection();
    return gameBoard.getNextNodeInDirection(entityPosition, entityDirection).Value.isSolid();
  }

  private void attemptToEat(IEntityObject entityToMove){
    Point entityPosition = getExistingEntityPositionByName(entityToMove);
    Directions entityDirection = getExistingEntityByName(entityToMove.getName()).getCurrentDirection();
    if (entityToMove.isHoldingDot()){
      entityToMove.increaseScore();
      entityToMove.setHoldingDot(false);
      gameBoard.getPreviousNodeInDirection(entityPosition, entityDirection).Value = new Space();
    }
  }

  private void movePositionOnBoard(IEntityObject entityToMove) {
    Point entityPosition = getExistingEntityPositionByName(entityToMove);
    Directions entityDirection = getExistingEntityByName(entityToMove.getName()).getCurrentDirection();
    if (gameBoard.getNextNodeInDirection(entityPosition, entityDirection).Value instanceof Dot)
    {
      entityToMove.setHoldingDot(true);
    }
    gameBoard.getNextNodeInDirection(entityPosition, entityDirection).Value = entityToMove;
    updateEntityPosition(entityToMove, gameBoard.getNextNodeInDirection(entityPosition, entityDirection).Position);
  }
}
