package model.entityobjects;

import model.Directions;

public interface IEntityObject {

  int getCurrentScore();

  Directions getCurrentDirection();

  String getName();
}
