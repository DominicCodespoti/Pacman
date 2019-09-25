package Controller;

import Model.EntityObjects.IEntityObject;

public interface IEnemyController {
    void moveEnemy(BoardController boardController, IEntityObject pacman, IEntityObject ghost);
}
