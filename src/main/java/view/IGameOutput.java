package view;

import world.Board;

public interface IGameOutput {

  void printBoard(Board board, int currentScore, int levelAmount);

  void printVictory();

  void printLose();
}
