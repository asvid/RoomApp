package asvid.github.io.roomapp.di

import asvid.github.io.roomapp.data.GistDatabase
import asvid.github.io.roomapp.data.gist.Gist
import asvid.github.io.roomapp.data.gist.GistRepository
import asvid.github.io.roomapp.data.owner.Owner
import asvid.github.io.roomapp.data.owner.OwnerRepository
import dagger.Module
import dagger.Provides
import io.realm.Realm
import javax.inject.Singleton

@Module(includes = [AppModule::class])
class DataModule {

    @Provides
    fun provideRealm(): Realm {
        GistDatabase.init()
        return Realm.getDefaultInstance()
    }

    @Singleton
    @Provides
    fun gistRepository(): GistRepository = GistRepository(provideRealm().where(Gist::class.java))

    @Singleton
    @Provides
    fun ownerRepository(): OwnerRepository = OwnerRepository(provideRealm().where(Owner::class.java))


}