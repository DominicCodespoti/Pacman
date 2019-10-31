package Model.EntityObjects;

import Model.Directions;

public interface IEntityObject {

  int getCurrentScore();

  Directions getCurrentDirection();

  String getName();
}
