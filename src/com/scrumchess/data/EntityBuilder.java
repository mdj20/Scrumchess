package com.scrumchess.data;
// class builds an entity

import com.google.appengine.api.datastore.Entity;

public interface EntityBuilder<T> {
	public Entity toEntity(T value);
}
