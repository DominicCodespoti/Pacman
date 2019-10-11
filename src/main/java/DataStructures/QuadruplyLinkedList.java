package DataStructures;

import Model.Directions;
import Model.GameObjects.IGameObject;
import Model.Point;

public class QuadruplyLinkedList {

  private final Node referenceNode;
  private Node rowIteratorNode;
  private final int width, height;

  public QuadruplyLinkedList(int width, int height) {
    this.width = width;
    this.height = height;
    referenceNode = new Node();
    Node columnIteratorNode;
    rowIteratorNode = columnIteratorNode = referenceNode;

    for (int I = 0; I < height; I++) {
      for (int J = 0; J < width; J++) {
        if (I == 0) {
          if (J < width - 1) {
            rowIteratorNode.right = new Node();
            rowIteratorNode.right.left = rowIteratorNode;
            rowIteratorNode = rowIteratorNode.right;
          }
        } else {
          if (J < width - 1) {
            rowIteratorNode.right = new Node();
            rowIteratorNode.up.down = rowIteratorNode;
            rowIteratorNode.right.left = rowIteratorNode;
            rowIteratorNode.right.up = rowIteratorNode.up.right;
            rowIteratorNode = rowIteratorNode.right;
          }
          if (J == width - 1) {
            rowIteratorNode.up.down = rowIteratorNode;
          }
        }
      }
      if (I < height - 1) {
        columnIteratorNode.down = new Node();
        columnIteratorNode.down.up = columnIteratorNode;
        rowIteratorNode = columnIteratorNode = columnIteratorNode.down;
      }
    }

    for (int I = 0; I < height; ++I) {
      for (int J = 0; J < width; ++J) {
        wrapNode(J, I);
        getNode(J, I).position = new Point(J, I);
      }
      System.out.println();
    }
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public void setValue(Point coordinate, IGameObject Value) {
    int X = coordinate.getX();
    int Y = coordinate.getY();
    rowIteratorNode = referenceNode;

    for (int I = 0; I < Y; ++I) {
      rowIteratorNode = rowIteratorNode.down;
    }

    for (int J = 0; J < X; ++J) {
      rowIteratorNode = rowIteratorNode.right;
    }

    rowIteratorNode.value = Value;
  }

  public IGameObject getValue(Point coordinate) {
    int X = coordinate.getX();
    int Y = coordinate.getY();

    rowIteratorNode = referenceNode;

    for (int I = 0; I < Y; ++I) {
      rowIteratorNode = rowIteratorNode.down;
    }

    for (int J = 0; J < X; ++J) {
      rowIteratorNode = rowIteratorNode.right;
    }

    return rowIteratorNode.value;
  }

  public Node nextNodeInDirection(Point coordinate, Directions currentDirection) {
    int X = coordinate.getX();
    int Y = coordinate.getY();

    rowIteratorNode = referenceNode;

    for (int I = 0; I < Y; ++I) {
      rowIteratorNode = rowIteratorNode.down;
    }

    for (int J = 0; J < X; ++J) {
      rowIteratorNode = rowIteratorNode.right;
    }

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

  public Node oppositeNodeInDirection(Point coordinate, Directions currentDirection) {
    int X = coordinate.getX();
    int Y = coordinate.getY();

    rowIteratorNode = referenceNode;

    for (int I = 0; I < Y; ++I) {
      rowIteratorNode = rowIteratorNode.down;
    }

    for (int J = 0; J < X; ++J) {
      rowIteratorNode = rowIteratorNode.right;
    }

    switch (currentDirection.getOppositeDirection()) {
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

  private Node getNode(int X, int Y) {
    rowIteratorNode = referenceNode;

    for (int I = 0; I < Y; ++I) {
      rowIteratorNode = rowIteratorNode.down;
    }

    for (int J = 0; J < X; ++J) {
      rowIteratorNode = rowIteratorNode.right;
    }

    return rowIteratorNode;
  }

  private void wrapNode(int I, int J) {
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