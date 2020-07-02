package com.bugevil.mhelmi.managepatients.di;

import androidx.lifecycle.ViewModel;
import com.bugevil.mhelmi.managepatients.di.annotitions.ViewModelKey;
import com.bugevil.mhelmi.managepatients.features.home.ui.viewmodels.PatientsViewModel;
import com.bugevil.mhelmi.managepatients.features.settings.ui.SettingsViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {
  @Binds @IntoMap @ViewModelKey(PatientsViewModel.class)
  abstract ViewModel providePatientsViewModel(PatientsViewModel patientsViewModel);

  @Binds @IntoMap @ViewModelKey(SettingsViewModel.class)
  abstract ViewModel provideSettingsViewModel(SettingsViewModel settingsViewModel);
}
