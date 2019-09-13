package LogicLayer;

import DataStructureLayer.MultiLayerLinkedList;

public class Board {

  protected MultiLayerLinkedList boardMap;

  public int getMapWidth() {
    return boardMap.GetWidth();
  }

  public int getMapHeights() {
    return boardMap.GetHeight();
  }

  public Board(int mapWidth, int mapHeight) {
    boardMap = new MultiLayerLinkedList(mapHeight, mapWidth);
  }
}
