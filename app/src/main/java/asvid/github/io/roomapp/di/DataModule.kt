package asvid.github.io.roomapp.di

import android.content.Context
import asvid.github.io.roomapp.data.GistDatabase
import asvid.github.io.roomapp.data.gist.GistRepository
import asvid.github.io.roomapp.data.owner.OwnerRepository
import dagger.Module
import dagger.Provides
import io.realm.Realm
import javax.inject.Singleton

@Module(includes = [AppModule::class])
class DataModule {

    @Provides
    fun provideRealm(context: Context): Realm {
        GistDatabase.init(context)
        return Realm.getDefaultInstance()
    }

    @Singleton
    @Provides
    fun gistRepository(context: Context): GistRepository = GistRepository(provideRealm(context))

    @Singleton
    @Provides
    fun ownerRepository(context: Context): OwnerRepository = OwnerRepository(provideRealm(context))


}