package View;

import Controller.BoardController;

public interface IGameOutput {

  void printBoard(BoardController boardController);

  void printVictory();

  void printLose();

  void printScore(int currentScore, int levelAmount);
}
