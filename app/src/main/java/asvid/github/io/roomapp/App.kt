package asvid.github.io.roomapp

import android.app.Activity
import android.app.Application
import asvid.github.io.roomapp.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class App : Application(), HasActivityInjector {

  @Inject
  lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

  override fun activityInjector(): AndroidInjector<Activity> {
    return dispatchingAndroidInjector
  }

  override fun onCreate() {
    super.onCreate()
    init()
  }

  private fun init() {
    DaggerAppComponent
        .builder()
        .application(this)
        .build()
        .inject(this)
  }
}