package Controller;

import DataStructures.QuadruplyLinkedList;
import DataStructures.Point;
import DataStructures.Directions;
import Model.Dot;
import Model.Ghost;
import Model.IEntityObject;
import Model.Pacman;
import Model.Space;
import Model.Wall;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class BoardController {

  private QuadruplyLinkedList gameBoard;
  private Map<IEntityObject, Point> currentEntities = new HashMap<>();

  public BoardController(int mapHeight, int mapWidth) {
    gameBoard = new QuadruplyLinkedList(mapWidth, mapHeight);

    Pacman pacman = new Pacman("Pacman");
    currentEntities.put(pacman, new Point(mapHeight / 2, mapWidth / 2));
    gameBoard.setValue(new Point(mapHeight / 2, mapWidth / 2), pacman);

    Ghost ghost = new Ghost("Ghost");
    currentEntities.put(ghost, new Point(mapHeight, mapWidth / 2));
    gameBoard.setValue(new Point(mapHeight, mapWidth / 2), ghost);

    gameBoard.setValue(new Point(0, mapWidth - 1), new Wall());
    gameBoard.setValue(new Point(1, mapWidth - 1), new Wall());
  }

  public Point getExistingEntityPosition(IEntityObject entityToMove) {
    Entry test = currentEntities.entrySet().stream()
        .filter(x -> x.getKey().getName().equals(entityToMove.getName())).findFirst().orElse(null);
    return (Point) test.getValue();
  }

  public IEntityObject getExistingEntityByName(String entityName) {
    return currentEntities.keySet().stream()
        .filter(x -> x.getName().equals(entityName))
        .findFirst()
        .orElse(null);
  }

  public void tryToRotateAndMoveEntity(IEntityObject entityToMove, Directions newDirection) {
    Directions oldDirection = entityToMove.getCurrentDirection();
    entityToMove.updateCurrentDirection(newDirection);
    if (isPathBlocked(entityToMove)) {
      entityToMove.updateCurrentDirection(oldDirection);
    } else {
      movePositionOnBoard(entityToMove);
      attemptToEat(entityToMove);
    }
  }

  public String getObjectRepresentationAtPosition(Point positionToGet) {
    return gameBoard.getValue(positionToGet).getString();
  }

  public int getBoardWidth() {
    return gameBoard.getWidth();
  }

  public int getBoardHeight() {
    return gameBoard.getHeight();
  }

  private void updateEntityPosition(IEntityObject entityToMove, Point newPosition) {
    Entry test = currentEntities.entrySet().stream()
        .filter(x -> x.getKey().getName().equals(entityToMove.getName())).findFirst().orElse(null);
    test.setValue(newPosition);
  }

  private boolean isPathBlocked(IEntityObject entityToCheck) {
    Point entityPosition = getExistingEntityPosition(entityToCheck);
    Directions entityDirection = getExistingEntityByName(entityToCheck.getName())
        .getCurrentDirection();
    return gameBoard.nextNodeInDirection(entityPosition, entityDirection).Value.isSolid();
  }

  private void attemptToEat(IEntityObject entityToMove) {
    Point entityPosition = getExistingEntityPosition(entityToMove);
    Directions entityDirection = getExistingEntityByName(entityToMove.getName())
        .getCurrentDirection();
    if (entityToMove instanceof Ghost
        && gameBoard.nextNodeInDirection(entityPosition, entityDirection).Value instanceof Pacman) {
      currentEntities.remove(gameBoard.nextNodeInDirection(entityPosition, entityDirection).Value);
      entityToMove.increaseScore();
    }
    if (entityToMove instanceof Ghost && entityToMove.isHoldingDot()) {
      gameBoard.oppositeNodeInDirection(entityPosition, entityDirection).Value = new Dot();
      entityToMove.setHoldingDot(false);
    } else {
      if (entityToMove.isHoldingDot()) {
        entityToMove.increaseScore();
        entityToMove.setHoldingDot(false);
      }
      gameBoard.oppositeNodeInDirection(entityPosition, entityDirection).Value = new Space();
    }
  }

  private void movePositionOnBoard(IEntityObject entityToMove) {
    Point entityPosition = getExistingEntityPosition(entityToMove);
    Directions entityDirection = getExistingEntityByName(entityToMove.getName())
        .getCurrentDirection();
    if (gameBoard.nextNodeInDirection(entityPosition, entityDirection).Value instanceof Dot) {
      entityToMove.setHoldingDot(true);
    }
    updateEntityPosition(entityToMove,
        gameBoard.nextNodeInDirection(entityPosition, entityDirection).Position);
    gameBoard.nextNodeInDirection(entityPosition, entityDirection).Value = entityToMove;
  }
}
