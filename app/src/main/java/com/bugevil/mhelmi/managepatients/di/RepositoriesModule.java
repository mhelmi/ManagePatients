package com.bugevil.mhelmi.managepatients.di;

import com.bugevil.mhelmi.managepatients.features.home.data.PatientsRepository;
import com.bugevil.mhelmi.managepatients.features.home.data.PatientsRepositoryImpl;
import dagger.Binds;
import dagger.Module;

@Module
public abstract class RepositoriesModule {

  @Binds
  abstract PatientsRepository bindPatientsRepository(PatientsRepositoryImpl patientsRepository);
}