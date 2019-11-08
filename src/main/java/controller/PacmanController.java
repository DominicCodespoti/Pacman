package controller;

import model.Directions;
import model.entityobjects.Pacman;
import model.gameobjects.IGameObject;
import model.gameobjects.Space;
import model.Point;

public class PacmanController {

  private final Board gameBoard;
  private final Pacman pacman;

  public PacmanController(Board gameBoard, Pacman pacman) {
    this.gameBoard = gameBoard;
    this.pacman = pacman;
  }

  public void move(Directions newDirection) {
    Directions oldDirection = pacman.getCurrentDirection();
    Point entityPosition = gameBoard.getExistingEntityPosition(pacman);

    pacman.updateCurrentDirection(newDirection);

    if (gameBoard.isPathBlocked(entityPosition, newDirection)) {
      pacman.updateCurrentDirection(oldDirection);
    }

    if (!gameBoard.isPathBlocked(entityPosition, pacman.getCurrentDirection())) {
      pacman.updateCurrentDirection(gameBoard.getExistingEntityByName(pacman.getName()).getCurrentDirection());
      movePositionOnBoard(entityPosition);
      attemptToEatDot();
      pacman.setIsMouthOpenToOpposite();
    }
  }

  private void attemptToEatDot() {
    Point entityPosition = gameBoard.getExistingEntityPosition(pacman);
    if (pacman.isHoldingDot()) {
      pacman.increaseScore();
      pacman.setHoldingDot(false);
    }
    gameBoard.nextNodeInDirection(entityPosition, pacman.getCurrentDirection().getOppositeDirection()).value = new Space();
  }

  private void movePositionOnBoard(Point entityPosition) {
    Directions entityDirection = pacman.getCurrentDirection();
    IGameObject nextObjectInDirection = (IGameObject) gameBoard.nextNodeInDirection(entityPosition, entityDirection).value;
    if (nextObjectInDirection.isEdible()) {
      pacman.setHoldingDot(true);
    }
    Point nextPoint = gameBoard.nextNodeInDirection(entityPosition, entityDirection).position;
    gameBoard.updateEntityPosition(pacman, nextPoint);
    gameBoard.nextNodeInDirection(entityPosition, entityDirection).value = pacman;
  }
}

