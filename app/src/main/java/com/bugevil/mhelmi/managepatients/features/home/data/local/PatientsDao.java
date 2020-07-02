package com.bugevil.mhelmi.managepatients.features.home.data.local;

import androidx.room.Dao;
import androidx.room.Query;
import com.bugevil.mhelmi.managepatients.features.home.data.models.Patient;
import com.bugevil.mhelmi.managepatients.utils.room.BaseDao;
import io.reactivex.Flowable;
import java.util.List;

@Dao
public abstract class PatientsDao implements BaseDao<Patient> {
  @Query("SELECT * FROM patient")
  public abstract Flowable<List<Patient>> getPatients();

  @Query("DELETE FROM patient")
  public abstract void deleteAll();
}
