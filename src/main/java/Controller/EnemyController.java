package Controller;

import Model.Point;
import Model.EntityObjects.IEntityObject;
import Utilities.DistanceCalculator;

public class EnemyController implements IEnemyController{

  @Override
  public void moveEnemy(BoardController boardController, IEntityObject pacman, IEntityObject ghost){
    if (boardController.getExistingEntityByName("Pacman") != null) {
      Point playerCurrentPosition = boardController.getExistingEntityPosition(pacman);
      Point enemyCurrentPosition = boardController.getExistingEntityPosition(ghost);

      boardController
          .tryToRotateAndMoveEntity(ghost, DistanceCalculator.findDirectionWithClosestPath(playerCurrentPosition,
              enemyCurrentPosition));
    }
  }
}
