package com.bugevil.mhelmi.managepatients.di;

import android.content.Context;
import androidx.room.Room;
import com.bugevil.mhelmi.managepatients.features.home.data.local.PatientsDao;
import com.bugevil.mhelmi.managepatients.utils.Const;
import com.bugevil.mhelmi.managepatients.utils.db.MainRoomDatabase;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class RoomModule {
  @Singleton @Provides
  MainRoomDatabase providesRoomDatabase(Context context) {
    return Room.databaseBuilder(context.getApplicationContext(),
      MainRoomDatabase.class, Const.KEY_DATABASE_NAME)
      // no Migration.
      .fallbackToDestructiveMigration()
      .build();
  }

  @Singleton @Provides
  PatientsDao providePatientsDao(MainRoomDatabase roomDatabase) {
    return roomDatabase.patientsDao();
  }
}