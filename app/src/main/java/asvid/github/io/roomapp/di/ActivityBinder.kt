package asvid.github.io.roomapp.di

import android.app.Activity
import asvid.github.io.roomapp.di.components.MainActivityComponent
import asvid.github.io.roomapp.di.components.OwnersActivityComponent
import asvid.github.io.roomapp.views.gists.MainActivity
import asvid.github.io.roomapp.views.owners.OwnersActivity
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class
ActivityBinder {

  @Binds
  @IntoMap
  @ActivityKey(MainActivity::class)
  abstract fun bindMainActivityInjectorFactory(
      builder: MainActivityComponent.Builder): AndroidInjector.Factory<out Activity>

  @Binds
  @IntoMap
  @ActivityKey(OwnersActivity::class)
  abstract fun bindOwnersActivityInjectorFactory(
      builder: OwnersActivityComponent.Builder): AndroidInjector.Factory<out Activity>
}