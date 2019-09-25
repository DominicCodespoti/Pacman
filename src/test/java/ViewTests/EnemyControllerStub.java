package ViewTests;

import Controller.BoardController;
import Controller.IEnemyController;
import Model.Directions;
import Model.EntityObjects.IEntityObject;

public class EnemyControllerStub implements IEnemyController {
    @Override
    public void moveEnemy(BoardController boardController, IEntityObject pacman, IEntityObject ghost) {
        if (boardController.getExistingEntityByName("Pacman") != null) {

            boardController.tryToRotateAndMoveEntity(ghost, Directions.Down);
        }
    }
}
