package controller;

import model.Directions;
import model.EntityObjects.Pacman;
import model.GameObjects.IGameObject;
import model.GameObjects.Space;
import model.Point;

public class PacmanController implements Movement {

  private final Board gameBoard;
  private final Pacman pacman;

  public PacmanController(Board gameBoard, Pacman pacman) {
    this.gameBoard = gameBoard;
    this.pacman = pacman;
  }

  @Override
  public void move(Directions newDirection) {
    Directions oldDirection = pacman.getCurrentDirection();
    Point entityPosition = gameBoard.getExistingEntityPosition(pacman);

    pacman.updateCurrentDirection(newDirection);

    if (gameBoard.isPathBlocked(entityPosition, newDirection)) {
      pacman.updateCurrentDirection(oldDirection);
    }

    if (!gameBoard.isPathBlocked(entityPosition, pacman.getCurrentDirection())) {
      Directions entityDirection = gameBoard.getExistingEntityByName(pacman.getName()).getCurrentDirection();
      movePositionOnBoard(entityPosition, entityDirection);
      attemptToEatDot(entityDirection);
      pacman.setIsMouthOpenToOpposite();
    }
  }

  private void attemptToEatDot(Directions entityDirection) {
    Point entityPosition = gameBoard.getExistingEntityPosition(pacman);
    if (pacman.isHoldingDot()) {
      pacman.increaseScore();
      pacman.setHoldingDot(false);
    }
    gameBoard.nextNodeInDirection(entityPosition, entityDirection.getOppositeDirection()).value = new Space();
  }

  private void movePositionOnBoard(Point entityPosition, Directions entityDirection) {
    IGameObject nextObjectInDirection = (IGameObject) gameBoard.nextNodeInDirection(entityPosition, entityDirection).value;
    if (nextObjectInDirection.isEdible()) {
      pacman.setHoldingDot(true);
    }
    Point nextPoint = gameBoard.nextNodeInDirection(entityPosition, entityDirection).position;
    gameBoard.updateEntityPosition(pacman, nextPoint);
    gameBoard.nextNodeInDirection(entityPosition, entityDirection).value = pacman;
  }
}

