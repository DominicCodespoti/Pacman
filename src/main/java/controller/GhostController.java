package controller;

import model.Directions;
import model.entityobjects.Ghost;
import model.entityobjects.IEntityObject;
import model.entityobjects.Pacman;
import model.gameobjects.Dot;
import model.gameobjects.IGameObject;
import model.gameobjects.Space;
import model.Point;

public class GhostController {

  private final Board gameBoard;
  private final Ghost ghost;

  public GhostController(Board gameBoard, Ghost ghost) {
    this.gameBoard = gameBoard;
    this.ghost = ghost;
  }

  public void move(Directions newDirection) {
    Point entityPosition = gameBoard.getExistingEntityPosition(ghost);
    if (ghost != null && !gameBoard.isPathBlocked(entityPosition, newDirection)) {
      ghost.updateCurrentDirection(newDirection);
      attemptToEatEntity(entityPosition);
      movePositionOnBoard(entityPosition);
      attemptToEatDot();
    }
  }

  private void attemptToEatEntity(Point entityPosition) {
    Directions entityDirection = ghost.getCurrentDirection();
    if (gameBoard.nextNodeInDirection(entityPosition, entityDirection).value instanceof Pacman) {
      gameBoard.removeEntity((IEntityObject) gameBoard.nextNodeInDirection(entityPosition, entityDirection).value);
      gameBoard.nextNodeInDirection(entityPosition, entityDirection).value = new Space();
      ghost.increaseScore();
    }
  }

  private void attemptToEatDot() {
    Point entityPosition = gameBoard.getExistingEntityPosition(ghost);
    Directions entityDirection = ghost.getCurrentDirection();
    if (ghost.isHoldingDot()) {
      gameBoard.nextNodeInDirection(entityPosition, entityDirection.getOppositeDirection()).value = new Dot();
      ghost.setHoldingDot(false);
    } else {
      gameBoard.nextNodeInDirection(entityPosition, entityDirection.getOppositeDirection()).value = new Space();
    }
  }

  private void movePositionOnBoard(Point entityPosition) {
    Directions entityDirection = ghost.getCurrentDirection();
    IGameObject nextObjectInDirection = (IGameObject) gameBoard.nextNodeInDirection(entityPosition, entityDirection).value;
    if (nextObjectInDirection.isEdible()) {
      ghost.setHoldingDot(true);
    }
    Point nextPoint = gameBoard.nextNodeInDirection(entityPosition, entityDirection).position;
    gameBoard.updateEntityPosition(ghost, nextPoint);
    gameBoard.nextNodeInDirection(entityPosition, entityDirection).value = ghost;
  }
}
