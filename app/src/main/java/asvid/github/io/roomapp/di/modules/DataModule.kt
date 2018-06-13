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
        return RealmConfiguration.Builder()
                .schemaVersion(1)
//        TODO(22) wstrzyknięcie utworzonej przez Daggera mapy migracji
                .migration(Migration(versionMigrations))
                .build()
    }

    @Singleton
    @Provides
/*       TODO(23) wstrzyknięcie do repozytoriów konfiguracji Realma, żeby repo mogło sobie utworzyć potrzebną instancję bazy -
         nie można wstrzyknąć instancji bazy, bo wtedy nie dałoby się korzystać z RX na osobnych wątkach,
         obiektów Realma nie można przenosić między wątkami, podobnie jak instancji bazy i dostalibyśmy wyjątek
*/
    fun gistRepository(realmConfiguration: RealmConfiguration): GistRepository = GistRepository(realmConfiguration)

    @Singleton
    @Provides
    fun ownerRepository(realmConfiguration: RealmConfiguration): OwnerRepository = OwnerRepository(realmConfiguration)


}