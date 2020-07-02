package com.bugevil.mhelmi.managepatients.di;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class PrefsModule {
  @Singleton
  @Provides
  SharedPreferences providePrefs(Context context) {
    return PreferenceManager.getDefaultSharedPreferences(context);
  }

  @Singleton
  @Provides
  SharedPreferences.Editor providePrefsEditor(SharedPreferences sharedPreferences) {
    return sharedPreferences.edit();
  }
}
