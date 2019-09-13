package LogicLayer;

import DataStructureLayer.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class GameMaster {

  private Board gameBoard;
  private Map<IEntityObject, Point> currentEntities = new HashMap<>();

  public GameMaster(Board gameBoard) {
    this.gameBoard = gameBoard;
  }

  public void addNewEntity(IEntityObject entityToAdd, Point entityPosition) {
    currentEntities.put(entityToAdd, entityPosition);
  }

  public Point getEntityPosition(IEntityObject entityToMove) {
    Entry test = currentEntities.entrySet().stream()
        .filter(x -> x.getKey().getName().equals(entityToMove.getName())).findFirst().orElse(null);
    return (Point) test.getValue();
  }

  public void updateEntityPosition(IEntityObject entityToMove, Point newPosition) {
    Entry test = currentEntities.entrySet().stream()
        .filter(x -> x.getKey().getName().equals(entityToMove.getName())).findFirst().orElse(null);
    test.setValue(newPosition);
  }

  public IEntityObject getExistingEntityByName(String entityName) {
    return currentEntities.keySet().stream()
        .filter(x -> x.getName().equals(entityName))
        .findAny()
        .orElse(null);
  }

  public void movePositionOnBoardValidation(IEntityObject entityToMove) {
    if (isPathUnblocked(entityToMove)) {
      return;
    }
    movePositionOnBoard(entityToMove);
  }

  public boolean isPathUnblocked(IEntityObject entityToCheck) {
    Point entityPosition = getEntityPosition(entityToCheck);
    Directions entityDirection = getExistingEntityByName(entityToCheck.getName())
        .getCurrentDirection();
    return gameBoard.boardMap
        .getNeighbouringNode(entityPosition, entityDirection).Value.isSolid();
  }

  private void movePositionOnBoard(IEntityObject entityToMove) {
    Point entityPosition = getEntityPosition(entityToMove);
    Directions entityDirection = getExistingEntityByName(entityToMove.getName())
        .getCurrentDirection();
    switch (entityToMove.getCurrentDirection()) {
      case Up:
        gameBoard.boardMap
            .getNeighbouringNode(entityPosition, entityDirection).Value = entityToMove;
        gameBoard.boardMap
            .getNeighbouringNode(entityPosition, entityDirection).Down.Value = entityToMove;
        updateEntityPosition(entityToMove,
            gameBoard.boardMap.getNeighbouringNode(entityPosition, entityDirection).Position);
        break;
      case Down:
        gameBoard.boardMap
            .getNeighbouringNode(entityPosition, entityDirection).Value = entityToMove;
        gameBoard.boardMap
            .getNeighbouringNode(entityPosition, entityDirection).Up.Value = entityToMove;
        updateEntityPosition(entityToMove,
            gameBoard.boardMap.getNeighbouringNode(entityPosition, entityDirection).Position);
        break;
      case Right:
        gameBoard.boardMap
            .getNeighbouringNode(entityPosition, entityDirection).Value = entityToMove;
        gameBoard.boardMap
            .getNeighbouringNode(entityPosition, entityDirection).Left.Value = entityToMove;
        updateEntityPosition(entityToMove,
            gameBoard.boardMap.getNeighbouringNode(entityPosition, entityDirection).Position);
        break;
      case Left:
        gameBoard.boardMap
            .getNeighbouringNode(entityPosition, entityDirection).Value = entityToMove;
        gameBoard.boardMap
            .getNeighbouringNode(entityPosition, entityDirection).Right.Value = entityToMove;
        updateEntityPosition(entityToMove,
            gameBoard.boardMap.getNeighbouringNode(entityPosition, entityDirection).Position);
        break;
    }
  }

  public void setGameBoardPosition(IEntityObject entityToSet, Point positionToSet) {
    gameBoard.boardMap.setValue(positionToSet, entityToSet);
  }
}
