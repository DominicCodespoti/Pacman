package DataStructures;

import Model.GameObjects.Dot;
import Model.IGameObject;

public class Node {

  public Node up, down, left, right;
  public Point position;
  public IGameObject value;

  public Node() {
    value = new Dot();
    position = new Point(0, 0);
    up = down = left = right = null;
  }
}