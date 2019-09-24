package Controller;

import Model.Directions;
import Model.Point;
import DataStructures.QuadruplyLinkedList;
import Model.EntityObjects.Ghost;
import Model.EntityObjects.Pacman;
import Model.GameObjects.Dot;
import Model.GameObjects.Space;
import Model.EntityObjects.IEntityObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class BoardController {

  private QuadruplyLinkedList gameBoard;
  private Map<IEntityObject, Point> currentEntities = new HashMap<>();

  public BoardController(IBoardGenerator boardGenerator) {
    gameBoard = boardGenerator.generateBoard();
    int boardHeight = gameBoard.getHeight();
    int boardWidth = gameBoard.getWidth();
    createEntity("Pacman", boardWidth / 2, boardHeight / 2, true);
    createEntity("Ghost1", 0, 0, false);
    createEntity("Ghost2", boardWidth - 1, boardHeight - 1, false);
  }

  public Point getExistingEntityPosition(IEntityObject entityToMove) {
    Entry test = currentEntities.entrySet().stream().filter(x -> x.getKey().getName().equals(entityToMove.getName()))
        .findFirst().orElse(null);
    return (Point) (test != null ? test.getValue() : null);
  }

  public IEntityObject getExistingEntityByName(String entityName) {
    return currentEntities.keySet().stream().filter(x -> x.getName().equals(entityName)).findFirst().orElse(null);
  }

  public int getEntityScore(IEntityObject entityToMove) {
    return entityToMove.getCurrentScore();
  }

  public void tryToRotateAndMoveEntity(IEntityObject entityToMove, Directions newDirection) {
    Directions oldDirection = entityToMove.getCurrentDirection();
    entityToMove.updateCurrentDirection(newDirection);
    if (getExistingEntityByName(entityToMove.getName()) != null && isPathBlocked(entityToMove)) {
      entityToMove.updateCurrentDirection(oldDirection);
    }
    if (getExistingEntityByName(entityToMove.getName()) != null && !isPathBlocked(entityToMove)) {
      attemptToEatEntity(entityToMove);
      if (getExistingEntityByName(entityToMove.getName()) != null) {
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

  private void createEntity(String entityName, int xPosition, int yPosition, boolean isPacman){
    if (isPacman) {
      Pacman pacman = new Pacman(entityName);
      currentEntities.put(pacman, new Point(xPosition, yPosition));
      gameBoard.setValue(new Point(xPosition, yPosition), pacman);
    }
    else {
      Ghost ghost = new Ghost(entityName);
      currentEntities.put(ghost, new Point(xPosition, yPosition));
      gameBoard.setValue(new Point(xPosition, yPosition), ghost);
    }
  }

  private void updateEntityPosition(IEntityObject entityToMove, Point newPosition) {
    Entry test = currentEntities.entrySet().stream().filter(x -> x.getKey().getName().equals(entityToMove.getName()))
        .findFirst().orElse(null);
    test.setValue(newPosition);
  }

  private boolean isPathBlocked(IEntityObject entityToCheck) {
    Point entityPosition = getExistingEntityPosition(entityToCheck);
    Directions entityDirection = getExistingEntityByName(entityToCheck.getName()).getCurrentDirection();
    return gameBoard.nextNodeInDirection(entityPosition, entityDirection).value.isSolid();
  }

  private void attemptToEatEntity(IEntityObject entityToMove) {
    Point entityPosition = getExistingEntityPosition(entityToMove);
    Directions entityDirection = getExistingEntityByName(entityToMove.getName()).getCurrentDirection();

    if (entityToMove instanceof Ghost && gameBoard
        .nextNodeInDirection(entityPosition, entityDirection).value instanceof Pacman) {
      gameBoard.setValue(entityPosition, new Space());
      currentEntities.remove(gameBoard.nextNodeInDirection(entityPosition, entityDirection).value);
      entityToMove.increaseScore();
    } else if (entityToMove instanceof Pacman && gameBoard
        .nextNodeInDirection(entityPosition, entityDirection).value instanceof Ghost) {
      gameBoard.setValue(entityPosition, new Space());
      ((Ghost) gameBoard.nextNodeInDirection(entityPosition, entityDirection).value).increaseScore();
      currentEntities.remove(entityToMove);
    }
  }

  private void attemptToEatDot(IEntityObject entityToMove) {
    Point entityPosition = getExistingEntityPosition(entityToMove);
    Directions entityDirection = getExistingEntityByName(entityToMove.getName()).getCurrentDirection();

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

  private void movePositionOnBoard(IEntityObject entityToMove) {
    Point entityPosition = getExistingEntityPosition(entityToMove);
    Directions entityDirection = getExistingEntityByName(entityToMove.getName()).getCurrentDirection();
    if (gameBoard.nextNodeInDirection(entityPosition, entityDirection).value.isEdible()) {
      entityToMove.setHoldingDot(true);
    }
    updateEntityPosition(entityToMove, gameBoard.nextNodeInDirection(entityPosition, entityDirection).position);
    gameBoard.nextNodeInDirection(entityPosition, entityDirection).value = entityToMove;
  }
}
