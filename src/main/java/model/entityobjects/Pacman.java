package model.entityobjects;

import controller.Board;
import model.Direction;
import model.Point;
import model.gameobjects.IGameObject;
import model.gameobjects.Space;

public class Pacman implements IEntityObject, IGameObject {

  private final String name;
  private Direction currentDirection;
  private int score;
  private boolean holdingDot;
  private boolean isMouthOpen = true;

  public Pacman(String name) {
    currentDirection = Direction.Up;
    this.name = name;
  }

  private void setIsMouthOpenToOpposite() {
    this.isMouthOpen = !this.isMouthOpen;
  }

  @Override
  public boolean isSolid() {
    return false;
  }

  @Override
  public boolean isEdible() {
    return true;
  }

  @Override
  public String getString() {
    switch (currentDirection) {
      case Up:
        return isMouthOpen ? "V" : "|";
      case Left:
        return isMouthOpen ? ">" : "-";
      case Right:
        return isMouthOpen ? "<" : "-";
      case Down:
        return isMouthOpen ? "^" : "|";
    }
    return "";
  }

  private void increaseScore() {
    score++;
  }

  private boolean isHoldingDot() {
    return holdingDot;
  }

  private void setHoldingDot(boolean isHolding) {
    holdingDot = isHolding;
  }

  public int getCurrentScore() {
    return score;
  }

  public void updateCurrentDirection(Direction newDirection) {
    currentDirection = newDirection;
  }

  @Override
  public Direction getCurrentDirection() {
    return currentDirection;
  }

  @Override
  public String getName() {
    return name;
  }


  public void move(Direction newDirection, Board gameBoard) {
    Point entityPosition = gameBoard.getPosition(this);
    if (gameBoard.isPathUnblocked(entityPosition, newDirection)) {
      updateCurrentDirection(newDirection);
      movePositionOnBoard(entityPosition, gameBoard);
      attemptToEatDot(gameBoard);
      setIsMouthOpenToOpposite();
    }
  }

  private void attemptToEatDot(Board gameBoard) {
    Point entityPosition = gameBoard.getPosition(this);
    Direction entityDirection = getCurrentDirection();
    if (isHoldingDot()) {
      increaseScore();
      setHoldingDot(false);
    }
    gameBoard.nextNodeInDirection(entityPosition, entityDirection.getOppositeDirection()).value = new Space();
  }

  private void movePositionOnBoard(Point entityPosition, Board gameBoard) {
    IGameObject nextObjectInDirection = (IGameObject) gameBoard.nextNodeInDirection(entityPosition, getCurrentDirection()).value;
    if (nextObjectInDirection.isEdible()) {
      setHoldingDot(true);
    }
    Point nextPosition = gameBoard.nextNodeInDirection(entityPosition, getCurrentDirection()).position;
    gameBoard.updateEntityPosition(this, nextPosition);
    gameBoard.nextNodeInDirection(entityPosition, getCurrentDirection()).value = this;
  }
}

