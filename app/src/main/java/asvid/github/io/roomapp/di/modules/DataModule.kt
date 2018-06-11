package asvid.github.io.roomapp.di.modules

import android.content.Context
import asvid.github.io.roomapp.data.gist.GistRepository
import asvid.github.io.roomapp.data.owner.OwnerRepository
import dagger.Module
import dagger.Provides
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Singleton


@Module(includes = [AppModule::class])
class DataModule {

    @Provides
    @Singleton
    fun provideRealmConfiguration(
            context: Context): RealmConfiguration {
        Realm.init(context)
        return RealmConfiguration.Builder()
                .schemaVersion(0)
                .build()
    }

    @Singleton
    @Provides
    fun gistRepository(realmConfiguration: RealmConfiguration): GistRepository = GistRepository(realmConfiguration)

    @Singleton
    @Provides
    fun ownerRepository(realmConfiguration: RealmConfiguration): OwnerRepository = OwnerRepository(realmConfiguration)


}