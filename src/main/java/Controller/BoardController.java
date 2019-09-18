package Controller;

import DataStructures.Directions;
import DataStructures.Point;
import DataStructures.QuadruplyLinkedList;
import Model.EntityObjects.Ghost;
import Model.EntityObjects.Pacman;
import Model.GameObjects.Dot;
import Model.GameObjects.Space;
import Model.IBoardGenerator;
import Model.IEntityObject;
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

    Pacman pacman = new Pacman("Pacman");
    currentEntities.put(pacman, new Point(boardWidth / 2, boardHeight / 2));
    gameBoard.setValue(new Point(boardWidth / 2, boardHeight / 2), pacman);

    Ghost ghost = new Ghost("Ghost");
    currentEntities.put(ghost, new Point(boardWidth, boardHeight / 2));
    gameBoard.setValue(new Point(boardWidth, boardHeight / 2), ghost);

    Ghost ghost2 = new Ghost("Ghost2");
    currentEntities.put(ghost2, new Point(boardWidth, 0));
    gameBoard.setValue(new Point(boardWidth, 0), ghost2);
  }

  public Point getExistingEntityPosition(IEntityObject entityToMove) {
    Entry test = currentEntities.entrySet().stream()
        .filter(x -> x.getKey().getName().equals(entityToMove.getName())).findFirst().orElse(null);
    return (Point) (test != null ? test.getValue() : null);
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

    if (entityToMove instanceof Ghost &&
        gameBoard.nextNodeInDirection(entityPosition, entityDirection).Value instanceof Pacman) {
      currentEntities.remove(gameBoard.nextNodeInDirection(entityPosition, entityDirection).Value);
      entityToMove.increaseScore();
    }

    if (entityToMove instanceof Pacman &&
        gameBoard.nextNodeInDirection(entityPosition, entityDirection).Value instanceof Ghost) {
      ((Ghost) gameBoard.nextNodeInDirection(entityPosition, entityDirection).Value)
          .increaseScore();
      currentEntities.remove(entityToMove);
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
