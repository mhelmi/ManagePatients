package com.bugevil.mhelmi.managepatients.features.home.domain;

import com.bugevil.mhelmi.managepatients.features.home.data.PatientsRepository;
import com.bugevil.mhelmi.managepatients.features.home.data.models.Patient;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import java.util.List;
import javax.inject.Inject;

public class DeleteAllPatientsUseCase {
  private PatientsRepository patientsRepository;

  @Inject
  public DeleteAllPatientsUseCase(PatientsRepository patientsRepository) {
    this.patientsRepository = patientsRepository;
  }

  public Completable excute() {
    return patientsRepository.deleteAll();
  }
}
