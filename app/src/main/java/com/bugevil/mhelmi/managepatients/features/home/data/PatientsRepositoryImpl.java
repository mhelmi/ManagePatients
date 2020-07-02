package com.bugevil.mhelmi.managepatients.features.home.data;

import com.bugevil.mhelmi.managepatients.features.home.data.local.PatientsDao;
import com.bugevil.mhelmi.managepatients.features.home.data.models.Patient;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import java.util.List;
import javax.inject.Inject;

public class PatientsRepositoryImpl implements PatientsRepository {

  private PatientsDao patientsDao;

  @Inject public PatientsRepositoryImpl(PatientsDao patientsDao) {
    this.patientsDao = patientsDao;
  }

  @Override public Flowable<List<Patient>> getPatients() {
    return patientsDao.getPatients();
  }

  @Override public Completable addNewPatient(Patient patient) {
    return Completable.fromAction(() -> patientsDao.insert(patient));
  }

  @Override public Completable deleteAll() {
    return Completable.fromAction(() -> patientsDao.deleteAll());
  }
}
