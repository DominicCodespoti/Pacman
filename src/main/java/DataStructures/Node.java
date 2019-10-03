package DataStructures;

import Model.GameObjects.Dot;
import Model.GameObjects.IGameObject;
import Model.Point;

public class Node {

  Node up, down, left, right;
  public Point position;
  public IGameObject value;

  Node() {
    value = new Dot();
    position = new Point(0, 0);
    up = down = left = right = null;
  }
}