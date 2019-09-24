package View;

public interface IGame {

  void setupGame();

  boolean checkWinConditionsMet();

  void runGame(int currentLevelIteration);

  void endGame(Boolean playerWin, int currentLevelIteration);
}
