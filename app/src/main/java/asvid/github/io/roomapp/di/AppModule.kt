package asvid.github.io.roomapp.di

import android.content.Context
import asvid.github.io.roomapp.App
import dagger.Module
import dagger.Provides

@Module(subcomponents = [MainActivityComponent::class])
class AppModule {

  @Provides
  internal fun context(application: App): Context {
    return application.applicationContext
  }
}