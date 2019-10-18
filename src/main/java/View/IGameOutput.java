package View;

import Controller.Board;

public interface IGameOutput {

  void printBoard(Board boardController, int currentScore, int levelAmount);

  void printVictory();

  void printLose();
}
