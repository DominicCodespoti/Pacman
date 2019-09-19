package View;

public interface IGameOutput {

  void printBoard();

  void printVictory();

  void printLose();

  void printScore(int currentScore, int levelAmount);
}
