package asvid.github.io.roomapp.di

import asvid.github.io.roomapp.views.gists.MainActivity
import asvid.github.io.roomapp.views.owners.OwnersActivity
import dagger.Module
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [MainActivityComponent.MainActivityModule::class])
interface MainActivityComponent : AndroidInjector<MainActivity> {

  @Subcomponent.Builder
  abstract class Builder : AndroidInjector.Builder<MainActivity>()

  @Module
  abstract class MainActivityModule

}

@Subcomponent(modules = [OwnersActivityComponent.OwnersActivityModule::class])
interface OwnersActivityComponent : AndroidInjector<OwnersActivity> {

  @Subcomponent.Builder
  abstract class Builder : AndroidInjector.Builder<OwnersActivity>()

  @Module
  abstract class OwnersActivityModule

}