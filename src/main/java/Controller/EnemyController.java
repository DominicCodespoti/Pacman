package Controller;

import DataStructures.Point;
import Model.DistanceCalculator;
import Model.IEntityObject;

public class EnemyController {
  public void moveEnemy(BoardController boardController, IEntityObject pacman, IEntityObject ghost){
    if (boardController.getExistingEntityByName("Pacman") != null) {
      Point playerCurrentPosition = boardController.getExistingEntityPosition(pacman);
      Point enemyCurrentPosition = boardController.getExistingEntityPosition(ghost);

      DistanceCalculator distanceCalculator = new DistanceCalculator(enemyCurrentPosition);
      boardController
          .tryToRotateAndMoveEntity(ghost, distanceCalculator.findDirectionWithClosestPath(playerCurrentPosition));
    }
  }
}
