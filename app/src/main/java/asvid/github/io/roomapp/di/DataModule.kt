package asvid.github.io.roomapp.di

import asvid.github.io.roomapp.data.gist.GistRepository
import asvid.github.io.roomapp.data.owner.OwnerRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [AppModule::class])
class DataModule {

    @Singleton
    @Provides
    fun gistRepository(): GistRepository = GistRepository()

    @Singleton
    @Provides
    fun ownerRepository(): OwnerRepository = OwnerRepository()


}