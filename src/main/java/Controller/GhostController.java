package Controller;

import Model.Directions;
import Model.EntityObjects.Ghost;
import Model.EntityObjects.IEntityObject;
import Model.EntityObjects.Pacman;
import Model.GameObjects.Dot;
import Model.GameObjects.IGameObject;
import Model.GameObjects.Space;
import Model.Point;

public class GhostController implements Movement {

  private Board gameBoard;
  private Ghost ghost;

  public GhostController(Board gameBoard, Ghost ghost) {
    this.gameBoard = gameBoard;
    this.ghost = ghost;
  }

  @Override
  public void move(Directions newDirection) {
    Point entityPosition = gameBoard.getExistingEntityPosition(ghost);
    if (ghost != null && !gameBoard.isPathBlocked(entityPosition, newDirection)) {
      if (ghost != null) {
        attemptToEatEntity(entityPosition, newDirection);
        movePositionOnBoard(entityPosition, newDirection);
        attemptToEatDot(newDirection);
      }
    }
  }

  private void attemptToEatEntity(Point entityPosition, Directions entityDirection) {
    if (gameBoard.nextNodeInDirection(entityPosition, entityDirection).value instanceof Pacman) {
      gameBoard.removeEntity((IEntityObject) gameBoard.nextNodeInDirection(entityPosition, entityDirection).value);
      gameBoard.nextNodeInDirection(entityPosition, entityDirection).value = new Space();
      ghost.increaseScore();
    }
  }

  private void attemptToEatDot(Directions entityDirection) {
    Point entityPosition = gameBoard.getExistingEntityPosition(ghost);
    if (ghost.isHoldingDot()) {
      gameBoard.nextNodeInDirection(entityPosition, entityDirection.getOppositeDirection()).value = new Dot();
      ghost.setHoldingDot(false);
    } else {
      gameBoard.nextNodeInDirection(entityPosition, entityDirection.getOppositeDirection()).value = new Space();
    }
  }

  private void movePositionOnBoard(Point entityPosition, Directions entityDirection) {
    IGameObject nextObjectInDirection = (IGameObject) gameBoard.nextNodeInDirection(entityPosition, entityDirection).value;
    if (nextObjectInDirection.isEdible()) {
      ghost.setHoldingDot(true);
    }
    Point nextPoint = gameBoard.nextNodeInDirection(entityPosition, entityDirection).position;
    gameBoard.updateEntityPosition(ghost, nextPoint);
    gameBoard.nextNodeInDirection(entityPosition, entityDirection).value = ghost;
  }
}
