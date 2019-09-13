package ConsoleLayer;

import LogicLayer.Board;

public class ConsolePrinter {

  private int mapWidth;
  private int mapHeight;
  private Board gameBoard;

  public ConsolePrinter(int mapWidth, int mapHeight, Board gameBoard) {
    this.gameBoard = gameBoard;
    this.mapHeight = mapHeight;
    this.mapWidth = mapWidth;
  }
}