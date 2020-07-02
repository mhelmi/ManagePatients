package com.bugevil.mhelmi.managepatients.features.home.data;

import com.bugevil.mhelmi.managepatients.features.home.data.models.Patient;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import java.util.List;

public interface PatientsRepository {
  Flowable<List<Patient>> getPatients();

  Completable addNewPatient(Patient patient);

  Completable deleteAll();
}
