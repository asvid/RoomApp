package asvid.github.io.roomapp.di

import asvid.github.io.roomapp.MainActivity
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