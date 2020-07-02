package com.bugevil.mhelmi.managepatients.features.home.domain;

import com.bugevil.mhelmi.managepatients.features.home.data.PatientsRepository;
import com.bugevil.mhelmi.managepatients.features.home.data.models.Patient;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import java.util.List;
import javax.inject.Inject;

public class GetPatientsUseCase {
  private PatientsRepository patientsRepository;

  @Inject
  public GetPatientsUseCase(PatientsRepository patientsRepository) {
    this.patientsRepository = patientsRepository;
  }

  public Flowable<List<Patient>> get() {
    return patientsRepository.getPatients();
  }
}
