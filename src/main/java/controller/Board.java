package controller;

import datastructures.Node;
import datastructures.QuadruplyLinkedList;
import model.Direction;
import model.entityobjects.Ghost;
import model.entityobjects.IEntityObject;
import model.entityobjects.Pacman;
import model.gameobjects.IGameObject;
import model.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Board {

  private final QuadruplyLinkedList gameBoard;
  private final Map<IEntityObject, Point> currentEntities = new HashMap<>();

  public Board(IBoardGenerator boardGenerator) {
    gameBoard = boardGenerator.generateBoard();
  }

  public Point getPosition(IEntityObject entityToMove) {
    Entry entityEntry = currentEntities.entrySet().stream()
        .filter(x -> x.getKey().getName().equals(entityToMove.getName())).findFirst().orElse(null);
    return (Point) (entityEntry != null ? entityEntry.getValue() : null);
  }

  public IEntityObject getExistingEntityByName(String entityName) {
    return currentEntities.keySet().stream().filter(x -> x.getName().equals(entityName)).findFirst().orElse(null);
  }

  public void updateEntityPosition(IEntityObject entityToMove, Point newPosition) {
    currentEntities.entrySet().stream().filter(x -> x.getKey().getName().equals(entityToMove.getName()))
        .forEach(x -> x.setValue(newPosition));
  }

  public void removeEntity(IEntityObject entityObject) {
    currentEntities.entrySet().removeIf(x -> x.getKey().getName().equals(entityObject.getName()));
  }

  public String getObjectRepresentationAtPosition(Point positionToGet) {
    IGameObject objectBeingRepresented = (IGameObject) gameBoard.getValue(positionToGet);
    return objectBeingRepresented.getString();
  }

  public int getBoardWidth() {
    return gameBoard.getWidth();
  }

  public int getBoardHeight() {
    return gameBoard.getHeight();
  }

  public Node nextNodeInDirection(Point entityPosition, Direction entityDirection) {
    return gameBoard.nextNodeInDirection(entityPosition, entityDirection);
  }

  public boolean isPathUnblocked(Point entityPosition, Direction entityDirection) {
    IGameObject objectInPath = (IGameObject) gameBoard.nextNodeInDirection(entityPosition, entityDirection).value;
    return !objectInPath.isSolid();
  }

  public Pacman createPacman(String entityName, Point position) {
    Pacman newEntity = new Pacman(entityName);
    currentEntities.put(newEntity, position);
    return newEntity;
  }

  public Ghost createGhost(String entityName, Point position) {
    Ghost newEntity = new Ghost(entityName);
    currentEntities.put(newEntity, position);
    return newEntity;
  }
}
