package world;

import datastructures.QuadruplyLinkedList;

public interface IBoardGenerator {

  // This could instead return a Board, as its name implies.
  QuadruplyLinkedList generateBoard();

  int scoreAmount();
}
