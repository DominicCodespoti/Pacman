package DataStructures;

import Model.Directions;
import Model.GameObjects.Dot;
import Model.Point;

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
    int X = coordinate.getX();
    int Y = coordinate.getY();
    rowIteratorNode = referenceNode;

    for (int I = 0; I < Y; ++I) {
      rowIteratorNode = rowIteratorNode.down;
    }

    for (int J = 0; J < X; ++J) {
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

  private Node getNode(int X, int Y) {
    setRowIteratorNode(new Point(X, Y));
    return rowIteratorNode;
  }

  public Node nextNodeInDirection(Point coordinate, Directions currentDirection) {
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

  private void wrapNode(int I, int J) { //TODO: Clean up
    rowIteratorNode = getNode(I, J);

    if (I == 0) {
      Node newNode = getNode(width - 1, J);
      rowIteratorNode = getNode(I, J);
      rowIteratorNode.left = newNode;
    }

    if (J == 0) {
      Node newNode = getNode(I, height - 1);
      rowIteratorNode = getNode(I, J);
      rowIteratorNode.up = newNode;
    }

    if (I == width - 1) {
      Node newNode = getNode(0, J);
      rowIteratorNode = getNode(I, J);
      rowIteratorNode.right = newNode;
    }

    if (J == height - 1) {
      Node newNode = getNode(I, 0);
      rowIteratorNode = getNode(I, J);
      rowIteratorNode.down = newNode;
    }
  }
}