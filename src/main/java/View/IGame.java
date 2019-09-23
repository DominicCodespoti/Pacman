package View;

public interface IGame {

  void setupGame();

  void runGame(int currentLevelIteration);

  void endGame(Boolean playerWin, int currentLevelIteration);
}
