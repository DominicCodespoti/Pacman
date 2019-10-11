package View;

import Controller.Board;

public interface IGameOutput {

  void printBoard(Board boardController);

  void printVictory();

  void printLose();

  void printScore(int currentScore, int levelAmount);
}
