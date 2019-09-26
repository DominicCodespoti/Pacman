package Controller;

import Utilities.DistanceCalculator;

public class EnemyController implements IEnemyController {

  @Override
  public void moveEnemy(BoardController boardController, String pacman, String ghost) {
    if (boardController.getExistingEntitiesEntry(pacman) != null) {

      boardController.tryToRotateAndMoveEntity(ghost,
          DistanceCalculator.findDirectionWithClosestPath(
              boardController.getExistingEntitiesEntry(pacman).getValue(),
              boardController.getExistingEntitiesEntry(ghost).getValue()));
    }
  }
}
