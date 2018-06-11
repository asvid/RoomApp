package asvid.github.io.roomapp.di.modules

import asvid.github.io.roomapp.services.GistLoadService
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ServiceBuilderModule {

    @ContributesAndroidInjector
    internal abstract fun contributeGistLoadService(): GistLoadService

}