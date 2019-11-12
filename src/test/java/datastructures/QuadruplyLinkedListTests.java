package datastructures;

import world.Direction;
import world.Point;
import gameobjects.Dot;
import org.junit.Assert;
import org.junit.Test;

public class QuadruplyLinkedListTests {

  @Test
  public void allNodesAreConnected() {
    QuadruplyLinkedList board = new QuadruplyLinkedList(5, 5);
    int failedNodeAmount = 0;
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        Point point = new Point(i, j);
        if (board.nextNodeInDirection(point, Direction.Up).value == null) {
          failedNodeAmount++;
        }
        if (board.nextNodeInDirection(point, Direction.Down).value == null) {
          failedNodeAmount++;
        }
        if (board.nextNodeInDirection(point, Direction.Left).value == null) {
          failedNodeAmount++;
        }
        if (board.nextNodeInDirection(point, Direction.Right).value == null) {
          failedNodeAmount++;
        }
      }
    }
    Assert.assertEquals(0, failedNodeAmount);
  }

  @Test
  public void allNodesInitializeAsDots(){
    QuadruplyLinkedList board = new QuadruplyLinkedList(5, 5);
    int dotAmount = 0;
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        Point point = new Point(i, j);
        if (board.nextNodeInDirection(point, Direction.Right).value instanceof Dot) {
          dotAmount++;
        }
      }
    }
    Assert.assertEquals(25, dotAmount);
  }
}
