package ViewTests;

import Controller.BoardController;
import Controller.IEnemyController;
import Model.Directions;

public class EnemyControllerStub implements IEnemyController {
    @Override
    public void moveEnemy(BoardController boardController, String pacman, String ghost) {
      if (boardController.getExistingEntitiesEntry("Pacman") != null) {

            boardController.tryToRotateAndMoveEntity(ghost, Directions.Down);
        }
    }
}
