package asvid.github.io.roomapp

import android.app.Activity
import android.app.Application
import android.app.Fragment
import android.app.Service
import android.util.Log
import asvid.github.io.roomapp.di.DaggerAppComponent
import com.amitshekhar.DebugDB
import dagger.android.*
import javax.inject.Inject

class App : Application(), HasActivityInjector, HasServiceInjector, HasFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>
    @Inject
    lateinit var dispatchingServiceInjector: DispatchingAndroidInjector<Service>
    @Inject
    lateinit var dispatchingFragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

    override fun serviceInjector(): AndroidInjector<Service> {
        return dispatchingServiceInjector
    }

    override fun fragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingFragmentInjector
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

        Log.d("ROOM_DB", "${DebugDB.getAddressLog()}")
    }
}