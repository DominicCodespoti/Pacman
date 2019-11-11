package datastructures;

import model.Direction;
import model.Point;
import model.gameobjects.Dot;

public class QuadruplyLinkedList {

  private final Node referenceNode;
  private final int width, height;
  private Node rowIteratorNode;

  public QuadruplyLinkedList(int width, int height) {
    this.width = width;
    this.height = height;
    referenceNode = new Node();
    Node columnIteratorNode;
    rowIteratorNode = columnIteratorNode = referenceNode;

    for (int I = 0; I < height; I++) {
      for (int J = 0; J < width; J++) {
        createRows(width, I, J);
      }
      columnIteratorNode = createColumns(height, columnIteratorNode, I);
    }

    for (int I = 0; I < height; ++I) {
      for (int J = 0; J < width; ++J) {
        wrapNode(J, I);
        getNode(J, I).value = new Dot();
        getNode(J, I).position = new Point(J, I);
      }
    }
  }

  private Node createColumns(int height, Node columnIteratorNode, int i) {
    if (i < height - 1) {
      columnIteratorNode.down = new Node();
      columnIteratorNode.down.up = columnIteratorNode;
      rowIteratorNode = columnIteratorNode = columnIteratorNode.down;
    }
    return columnIteratorNode;
  }

  private void createRows(int width, int i, int j) {
    if (i == 0) {
      if (j < width - 1) {
        rowIteratorNode.right = new Node();
        rowIteratorNode.right.left = rowIteratorNode;
        rowIteratorNode = rowIteratorNode.right;
      }
    } else {
      if (j < width - 1) {
        rowIteratorNode.right = new Node();
        rowIteratorNode.up.down = rowIteratorNode;
        rowIteratorNode.right.left = rowIteratorNode;
        rowIteratorNode.right.up = rowIteratorNode.up.right;
        rowIteratorNode = rowIteratorNode.right;
      }
      if (j == width - 1) {
        rowIteratorNode.up.down = rowIteratorNode;
      }
    }
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  private void setRowIteratorNode(Point coordinate) {
    int x = coordinate.getX();
    int y = coordinate.getY();
    rowIteratorNode = referenceNode;

    for (int i = 0; i < y; ++i) {
      rowIteratorNode = rowIteratorNode.down;
    }

    for (int i = 0; i < x; ++i) {
      rowIteratorNode = rowIteratorNode.right;
    }
  }

  public void setValue(Point coordinate, Object Value) {
    setRowIteratorNode(coordinate);
    rowIteratorNode.value = Value;
  }

  public Object getValue(Point coordinate) {
    setRowIteratorNode(coordinate);
    return rowIteratorNode.value;
  }

  private Node getNode(int x, int y) {
    setRowIteratorNode(new Point(x, y));
    return rowIteratorNode;
  }

  public Node nextNodeInDirection(Point coordinate, Direction currentDirection) {
    setRowIteratorNode(coordinate);
    switch (currentDirection) {
      case Up:
        return rowIteratorNode.up;
      case Down:
        return rowIteratorNode.down;
      case Left:
        return rowIteratorNode.left;
      case Right:
        return rowIteratorNode.right;
    }
    return null;
  }

  private void wrapNode(int x, int y) {
    Node currentNode = getNode(x, y);
    rowIteratorNode = currentNode;

    if (x == 0) {
      rowIteratorNode.left = getNodeAtEdge(y, width - 1, currentNode);
    }

    if (y == 0) {
      rowIteratorNode.up = getNodeAtEdge(height - 1, x, currentNode);
    }

    if (x == width - 1) {
      rowIteratorNode.right = getNodeAtEdge(y, 0, currentNode);
    }

    if (y == height - 1) {
      rowIteratorNode.down = getNodeAtEdge(0, x, currentNode);
    }
  }

  private Node getNodeAtEdge(int y, int x, Node node) {
    Node nodeAtEdge = getNode(x, y);
    rowIteratorNode = node;
    return nodeAtEdge;
  }
}