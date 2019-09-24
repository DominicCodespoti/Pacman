package Controller;

import DataStructures.QuadruplyLinkedList;

public interface IBoardGenerator {

  QuadruplyLinkedList generateBoard();

  int scoreAmount();
}
