package view;

import controller.Board;

public interface IGameOutput {

  void printBoard(Board boardController, int currentScore, int levelAmount);

  void printVictory();

  void printLose();
}
