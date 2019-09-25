package View;

public interface IGame {

  void setupGame();

  boolean isPacmanAliveOrDotsUneaten();

  void runGame(int currentLevelIteration);

  void endGame(Boolean playerWin, int currentLevelIteration);
}
