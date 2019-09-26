package Controller;

import DataStructures.QuadruplyLinkedList;
import Model.Directions;
import Model.EntityObjects.Ghost;
import Model.EntityObjects.IEntityObject;
import Model.EntityObjects.Pacman;
import Model.GameObjects.Dot;
import Model.GameObjects.Space;
import Model.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class BoardController {

  private final QuadruplyLinkedList gameBoard;
  private final Map<IEntityObject, Point> currentEntities = new HashMap<>();

  public BoardController(IBoardGenerator boardGenerator) {
    gameBoard = boardGenerator.generateBoard();
  }

  public Point getExistingEntityPosition(IEntityObject entityToMove) {
    Entry entityEntry = currentEntities.entrySet().stream()
        .filter(x -> x.getKey().getName().equals(entityToMove.getName()))
        .findFirst().orElse(null);
    return (Point) (entityEntry != null ? entityEntry.getValue() : null);
  }

  public IEntityObject getExistingEntityByName(String entityName) {
    return currentEntities.keySet().stream()
        .filter(x -> x.getName().equals(entityName))
        .findFirst().orElse(null);
  }

  public int getEntityScore(IEntityObject entityToMove) {
    return entityToMove.getCurrentScore();
  }

  public void tryToRotateAndMoveEntity(IEntityObject entityToMove, Directions newDirection) {
    Directions oldDirection = entityToMove.getCurrentDirection();
    Point entityPosition = getExistingEntityPosition(entityToMove);

    entityToMove.updateCurrentDirection(newDirection);

    if (getExistingEntityByName(entityToMove.getName()) != null && isPathBlocked(entityPosition, newDirection)) {
      entityToMove.updateCurrentDirection(oldDirection);
    }

    Directions entityDirection = getExistingEntityByName(entityToMove.getName()).getCurrentDirection();

    if (getExistingEntityByName(entityToMove.getName()) != null && !isPathBlocked(entityPosition, entityDirection)) {
      attemptToEatEntity(entityToMove, entityPosition, entityDirection);

      if (getExistingEntityByName(entityToMove.getName()) != null) {
        movePositionOnBoard(entityToMove, entityPosition, entityDirection);
        attemptToEatDot(entityToMove, entityPosition, entityDirection);
      }
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

  public void alternatePacmanMouth(Pacman entityToAlternate) {
    entityToAlternate.setIsMouthOpenToOpposite();
  }

  public void createEntity(String entityName, int xPosition, int yPosition, boolean isPacman) {
    if (isPacman) {
      Pacman pacman = new Pacman(entityName);
      currentEntities.put(pacman, new Point(xPosition, yPosition));
      gameBoard.setValue(new Point(xPosition, yPosition), pacman);
    } else {
      Ghost ghost = new Ghost(entityName);
      currentEntities.put(ghost, new Point(xPosition, yPosition));
      gameBoard.setValue(new Point(xPosition, yPosition), ghost);
    }
  }

  private void updateEntityPosition(IEntityObject entityToMove, Point newPosition) {
    currentEntities.entrySet().stream()
        .filter(x -> x.getKey().getName().equals(entityToMove.getName()))
        .forEach(x -> x.setValue(newPosition));
  }

  private boolean isPathBlocked(Point entityPosition, Directions entityDirection) {
    return gameBoard.nextNodeInDirection(entityPosition, entityDirection).value.isSolid();
  }

  private void attemptToEatEntity(IEntityObject entityToMove, Point entityPosition, Directions entityDirection) {
    if (entityToMove instanceof Ghost &&
        gameBoard.nextNodeInDirection(entityPosition, entityDirection).value instanceof Pacman) {
      gameBoard.setValue(entityPosition, new Space());
      IEntityObject entityToRemove = (IEntityObject) gameBoard.nextNodeInDirection(entityPosition, entityDirection).value;
      currentEntities.remove(entityToRemove);
      entityToMove.increaseScore();
    } else if (entityToMove instanceof Pacman &&
        gameBoard.nextNodeInDirection(entityPosition, entityDirection).value instanceof Ghost) {
      gameBoard.setValue(entityPosition, new Space());
      ((Ghost) gameBoard.nextNodeInDirection(entityPosition, entityDirection).value).increaseScore();
      currentEntities.remove(entityToMove);
    }
  }

  private void attemptToEatDot(IEntityObject entityToMove, Point entityPosition, Directions entityDirection) {
    if (entityToMove instanceof Ghost && entityToMove.isHoldingDot()) {
      gameBoard.oppositeNodeInDirection(entityPosition, entityDirection).value = new Dot();
      entityToMove.setHoldingDot(false);
    } else {
      if (entityToMove.isHoldingDot()) {
        entityToMove.increaseScore();
        entityToMove.setHoldingDot(false);
      }
      gameBoard.oppositeNodeInDirection(entityPosition, entityDirection).value = new Space();
    }
  }

  private void movePositionOnBoard(IEntityObject entityToMove, Point entityPosition, Directions entityDirection) {
    if (gameBoard.nextNodeInDirection(entityPosition, entityDirection).value.isEdible()) {
      entityToMove.setHoldingDot(true);
    }
    updateEntityPosition(entityToMove, gameBoard.nextNodeInDirection(entityPosition, entityDirection).position);
    gameBoard.nextNodeInDirection(entityPosition, entityDirection).value = entityToMove;
  }
}
