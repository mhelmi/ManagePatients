package com.bugevil.mhelmi.managepatients.features.home.domain;

import com.bugevil.mhelmi.managepatients.features.home.data.PatientsRepository;
import com.bugevil.mhelmi.managepatients.features.home.data.models.Patient;
import io.reactivex.Completable;
import javax.inject.Inject;

public class AddPatientUseCase {
  private PatientsRepository patientsRepository;

  @Inject
  public AddPatientUseCase(PatientsRepository patientsRepository) {
    this.patientsRepository = patientsRepository;
  }

  public Completable excute(Patient patient) {
    return patientsRepository.addNewPatient(patient);
  }
}
