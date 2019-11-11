package model.entityobjects;

import controller.Board;
import model.Direction;
import model.Point;
import model.gameobjects.Dot;
import model.gameobjects.IGameObject;
import model.gameobjects.Space;

public class Ghost implements IEntityObject, IGameObject {

  private final String name;
  private Direction currentDirection;
  private int score = 0;
  private boolean holdingDot = false;

  public Ghost(String name) {
    currentDirection = Direction.Up;
    this.name = name;
  }

  @Override
  public boolean isSolid() {
    return true;
  }

  @Override
  public boolean isEdible() {
    return false;
  }

  @Override
  public String getString() {
    return "G";
  }

  public int getCurrentScore() {
    return score;
  }

  @Override
  public Direction getCurrentDirection() {
    return currentDirection;
  }

  @Override
  public String getName() {
    return name;
  }

  private void updateCurrentDirection(Direction newDirection) {
    currentDirection = newDirection;
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

  public void move(Direction newDirection, Board gameBoard) {
    Point entityPosition = gameBoard.getPosition(this);
    if (gameBoard.isPathUnblocked(entityPosition, newDirection)) {
      updateCurrentDirection(newDirection);
      attemptToEatEntity(entityPosition, gameBoard);
      movePositionOnBoard(entityPosition, gameBoard);
      attemptToEatDot(gameBoard);
    }
  }

  private void attemptToEatEntity(Point entityPosition, Board gameBoard) {
    Direction entityDirection = getCurrentDirection();
    if (gameBoard.nextNodeInDirection(entityPosition, entityDirection).value instanceof Pacman) {
      gameBoard.removeEntity((IEntityObject) gameBoard.nextNodeInDirection(entityPosition, entityDirection).value);
      gameBoard.nextNodeInDirection(entityPosition, entityDirection).value = new Space();
      increaseScore();
    }
  }

  private void attemptToEatDot(Board gameBoard) {
    Point entityPosition = gameBoard.getPosition(this);
    Direction entityDirection = getCurrentDirection();
    if (isHoldingDot()) {
      gameBoard.nextNodeInDirection(entityPosition, entityDirection.getOppositeDirection()).value = new Dot();
      setHoldingDot(false);
    } else {
      gameBoard.nextNodeInDirection(entityPosition, entityDirection.getOppositeDirection()).value = new Space();
    }
  }

  private void movePositionOnBoard(Point entityPosition, Board gameBoard) {
    Direction entityDirection = getCurrentDirection();
    IGameObject nextObjectInDirection = (IGameObject) gameBoard.nextNodeInDirection(entityPosition, entityDirection).value;
    if (nextObjectInDirection.isEdible()) {
      setHoldingDot(true);
    }
    Point nextPoint = gameBoard.nextNodeInDirection(entityPosition, entityDirection).position;
    gameBoard.updateEntityPosition(this, nextPoint);
    gameBoard.nextNodeInDirection(entityPosition, entityDirection).value = this;
  }
}

