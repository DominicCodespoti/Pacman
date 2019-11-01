package controller;

import datastructures.QuadruplyLinkedList;

public interface IBoardGenerator {

  QuadruplyLinkedList generateBoard();

  int scoreAmount();
}
