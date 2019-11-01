package model.EntityObjects;

import model.Directions;

public interface IEntityObject {

  int getCurrentScore();

  Directions getCurrentDirection();

  String getName();
}
