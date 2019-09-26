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

  private QuadruplyLinkedList gameBoard;
  private Map<IEntityObject, Point> currentEntities = new HashMap<>();

  public BoardController(IBoardGenerator boardGenerator) {
    gameBoard = boardGenerator.generateBoard();
  }

  public Entry<IEntityObject, Point> getExistingEntitiesEntry(String entityName) {
    return currentEntities.entrySet().stream()
        .filter(x -> x.getKey().getName().equals(entityName))
        .findFirst()
        .orElse(null);
  }

  public int getEntityScore(String entityToMove) {
    return getExistingEntitiesEntry(entityToMove).getKey().getCurrentScore();
  }

  public void tryToRotateAndMoveEntity(String entityToMove, Directions newDirection) {
    Entry<IEntityObject, Point> entityEntry = getExistingEntitiesEntry(entityToMove);
    Directions oldDirection = entityEntry.getKey().getCurrentDirection();
    entityEntry.getKey().updateCurrentDirection(newDirection);
    if (getExistingEntitiesEntry(entityToMove) != null && isPathBlocked(entityToMove)) {
      entityEntry.getKey().updateCurrentDirection(oldDirection);
    }
    if (getExistingEntitiesEntry(entityToMove) != null && !isPathBlocked(entityToMove)) {
      attemptToEatEntity(entityToMove);
      if (getExistingEntitiesEntry(entityToMove) != null) {
        movePositionOnBoard(entityToMove);
        attemptToEatDot(entityToMove);
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

  public void alternatePacmanMouth(String entityToAlternate) {
    Pacman pacman = (Pacman) getExistingEntitiesEntry(entityToAlternate).getKey();
    pacman.setIsMouthOpenToOpposite();
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
        .findFirst()
        .ifPresent(entityMapEntry -> entityMapEntry.setValue(newPosition));
  }

  private boolean isPathBlocked(String entityToCheck) {
    return gameBoard.nextNodeInDirection(
        getExistingEntitiesEntry(entityToCheck).getValue(),
        getExistingEntitiesEntry(entityToCheck).getKey().getCurrentDirection())
        .value.isSolid();
  }

  private void attemptToEatEntity(String entityToMove) {
    Entry<IEntityObject, Point> entityEntry = getExistingEntitiesEntry(entityToMove);
    if (entityEntry.getKey() instanceof Ghost &&
        gameBoard.nextNodeInDirection(entityEntry.getValue(), entityEntry.getKey().getCurrentDirection()).value
            instanceof Pacman) {

      gameBoard.setValue(entityEntry.getValue(), new Space());
      currentEntities.remove(gameBoard.nextNodeInDirection(entityEntry.getValue(), entityEntry.getKey().getCurrentDirection()).value);
      entityEntry.getKey().increaseScore();
    } else if (entityEntry.getKey() instanceof Pacman && gameBoard
        .nextNodeInDirection(entityEntry.getValue(), entityEntry.getKey().getCurrentDirection()).value instanceof Ghost) {
      gameBoard.setValue(entityEntry.getValue(), new Space());
      ((Ghost) gameBoard.nextNodeInDirection(entityEntry.getValue(), entityEntry.getKey().getCurrentDirection()).value).increaseScore();
      currentEntities.remove(entityEntry.getKey());
    }
  }

  private void attemptToEatDot(String entityToMove) {
    Entry<IEntityObject, Point> entityEntry = getExistingEntitiesEntry(entityToMove);
    if (entityEntry.getKey() instanceof Ghost && entityEntry.getKey().isHoldingDot()) {
      gameBoard.oppositeNodeInDirection(entityEntry.getValue(), entityEntry.getKey().getCurrentDirection()).value = new Dot();
      entityEntry.getKey().setHoldingDot(false);
    } else {
      if (entityEntry.getKey().isHoldingDot()) {
        entityEntry.getKey().increaseScore();
        entityEntry.getKey().setHoldingDot(false);
      }
      gameBoard.oppositeNodeInDirection(entityEntry.getValue(), entityEntry.getKey().getCurrentDirection()).value = new Space();
    }
  }

  private void movePositionOnBoard(String entityToMove) {
    Entry<IEntityObject, Point> entityEntry = getExistingEntitiesEntry(entityToMove);
    if (gameBoard.nextNodeInDirection(entityEntry.getValue(), entityEntry.getKey().getCurrentDirection()).value.isEdible()) {
      entityEntry.getKey().setHoldingDot(true);
    }
    updateEntityPosition(entityEntry.getKey(), gameBoard.nextNodeInDirection(entityEntry.getValue(), entityEntry.getKey().getCurrentDirection()).position);
    gameBoard.nextNodeInDirection(entityEntry.getValue(), entityEntry.getKey().getCurrentDirection()).value = entityEntry.getKey();
  }
}
