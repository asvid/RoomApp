package asvid.github.io.roomapp.di.modules

import android.content.Context
import asvid.github.io.roomapp.data.gist.GistRepository
import asvid.github.io.roomapp.data.migration.Migration
import asvid.github.io.roomapp.data.migration.VersionMigration
import asvid.github.io.roomapp.data.owner.OwnerRepository
import dagger.Module
import dagger.Provides
import io.realm.Realm
import io.realm.RealmConfiguration
import timber.log.Timber
import javax.inject.Provider
import javax.inject.Singleton


@Module(includes = [AppModule::class, MigrationsModule::class])
class DataModule {

    @Provides
    @Singleton
    fun provideRealmConfiguration(
            context: Context,
            versionMigrations: Map<Int, @JvmSuppressWildcards Provider<VersionMigration>>): RealmConfiguration {
        Realm.init(context)
        Timber.d("daggerMap: $versionMigrations")
        return RealmConfiguration.Builder()
                .schemaVersion(2)
                .migration(Migration(versionMigrations))
//                .deleteRealmIfMigrationNeeded()
                .build()
    }

    @Provides
    @Singleton
    fun provideRealm(realmConfiguration: RealmConfiguration): Realm {
        return Realm.getInstance(realmConfiguration)
    }

    @Singleton
    @Provides
    fun gistRepository(realm: Realm): GistRepository = GistRepository(realm)

    @Singleton
    @Provides
    fun ownerRepository(realm: Realm): OwnerRepository = OwnerRepository(realm)


}