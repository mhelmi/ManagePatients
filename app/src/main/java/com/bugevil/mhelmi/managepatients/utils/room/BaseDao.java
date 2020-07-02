package com.bugevil.mhelmi.managepatients.utils.room;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;
import java.util.List;

public interface BaseDao<T> {

  /**
   * Insert an object in the database.
   *
   * @param item the object to be inserted.
   */
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insert(T item);

  /**
   * Insert an list of objects in the database.
   *
   * @param itemList the objects to be inserted.
   */
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insert(List<T> itemList);

  /**
   * Update an object from the database.
   *
   * @param item the object to be updated
   */
  @Update
  void update(T item);

  /**
   * Delete an object from the database
   *
   * @param item the object to be deleted
   */
  @Delete
  void delete(T item);
}