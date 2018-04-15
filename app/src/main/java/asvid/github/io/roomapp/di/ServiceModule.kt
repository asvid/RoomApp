package asvid.github.io.roomapp.di

import asvid.github.io.roomapp.services.GistLoadService
import dagger.Component
import dagger.Module
import dagger.Provides

@Module(includes = [DataModule::class])
class ServiceModule(var gistLoadService: GistLoadService) {

  @Provides
  fun provideGistLoadService(): GistLoadService {
    return gistLoadService
  }
}

@Component(modules = [ServiceModule::class])
internal interface ServiceComponent {
  fun inject(service: GistLoadService)
}