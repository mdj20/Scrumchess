package com.scrumchess.data;



import java.util.List;

import com.google.appengine.api.datastore.Entity;

/**Defines an object that serves as an Entity object translator tool.
 * 
 * @author Matthew D. Jeffreys
 *
 */

public interface ObjectEntityAdapter<T>  {
	public T toObject(Entity entity);
	public List<T> toObject(Iterable<Entity> entitiy);
	public Entity toEntity(T object);
	public List<Entity> toEntity(List<T> object);
}
