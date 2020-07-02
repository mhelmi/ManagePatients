package com.bugevil.mhelmi.managepatients.di;

import android.content.Context;
import com.bugevil.mhelmi.managepatients.features.home.ui.activities.MainActivity;
import com.bugevil.mhelmi.managepatients.features.home.ui.fragments.HomeFragment;
import com.bugevil.mhelmi.managepatients.features.settings.ui.SettingsFragment;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = {
  ContextModule.class, ViewModelModule.class, RepositoriesModule.class, RoomModule.class,
  PrefsModule.class
})
public interface AppComponent {
  Context getContext();

  // activities
  void inject(MainActivity mainActivity);

  // fragments
  void inject(HomeFragment homeFragment);

  void inject(SettingsFragment settingsFragment);

  @Component.Factory
  interface Factory {
    AppComponent create(ContextModule contextModule);
  }
}
