package Controller;

import Model.Directions;
import Model.EntityObjects.Pacman;
import Model.GameObjects.Space;
import Model.Point;

public class PacmanController implements Movement {

  private Board gameBoard;
  private Pacman pacman;

  public PacmanController(Board gameBoard, Pacman pacman) {
    this.gameBoard = gameBoard;
    this.pacman = pacman;
  }

  @Override
  public void move(Directions newDirection) {
    Directions oldDirection = pacman.getCurrentDirection();
    Point entityPosition = gameBoard.getExistingEntityPosition(pacman);

    pacman.updateCurrentDirection(newDirection);

    if (pacman != null && gameBoard.isPathBlocked(entityPosition, newDirection)) {
      pacman.updateCurrentDirection(oldDirection);
    }

    if (pacman != null && !gameBoard.isPathBlocked(entityPosition, pacman.getCurrentDirection())) {
      Directions entityDirection = gameBoard.getExistingEntityByName(pacman.getName()).getCurrentDirection();
      if (pacman != null) {
        movePositionOnBoard(entityPosition, entityDirection);
        attemptToEatDot(entityDirection);
        pacman.setIsMouthOpenToOpposite();
      }
    }
  }

  private void attemptToEatDot(Directions entityDirection) {
    Point entityPosition = gameBoard.getExistingEntityPosition(pacman);
    if (pacman.isHoldingDot()) {
      pacman.increaseScore();
      pacman.setHoldingDot(false);
    }
    gameBoard.oppositeNodeInDirection(entityPosition, entityDirection).value = new Space();
  }

  private void movePositionOnBoard(Point entityPosition, Directions entityDirection) {
    if (gameBoard.nextNodeInDirection(entityPosition, entityDirection).value.isEdible()) {
      pacman.setHoldingDot(true);
    }
    Point nextPoint = gameBoard.nextNodeInDirection(entityPosition, entityDirection).position;
    gameBoard.updateEntityPosition(pacman, nextPoint);
    gameBoard.nextNodeInDirection(entityPosition, entityDirection).value = pacman;
  }
}

