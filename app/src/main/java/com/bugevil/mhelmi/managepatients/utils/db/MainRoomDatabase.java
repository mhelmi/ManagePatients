package com.bugevil.mhelmi.managepatients.utils.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.bugevil.mhelmi.managepatients.features.home.data.local.PatientsDao;
import com.bugevil.mhelmi.managepatients.features.home.data.models.Patient;

@Database(entities = {
  Patient.class,
}, version = 1 /* live version = 0 */, exportSchema = false)
@TypeConverters({
})
public abstract class MainRoomDatabase extends RoomDatabase {

  public abstract PatientsDao patientsDao();
}