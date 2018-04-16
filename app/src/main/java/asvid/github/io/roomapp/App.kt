package asvid.github.io.roomapp

import android.app.Activity
import android.app.Application
import android.app.Service
import asvid.github.io.roomapp.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasServiceInjector
import javax.inject.Inject

class App : Application(), HasActivityInjector, HasServiceInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>
    @Inject
    lateinit var dispatchingServiceInjector: DispatchingAndroidInjector<Service>

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

    override fun serviceInjector(): AndroidInjector<Service> {
        return dispatchingServiceInjector
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