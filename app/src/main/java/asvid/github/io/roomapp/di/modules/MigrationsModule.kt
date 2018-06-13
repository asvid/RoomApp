package asvid.github.io.roomapp.di.modules

import asvid.github.io.roomapp.data.migration.Version0Migration
import asvid.github.io.roomapp.data.migration.VersionMigration
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntKey
import dagger.multibindings.IntoMap

// TODO(21) moduł z migracjami do Realm, z których Dagger tworzy mapę <Wersja, Migracja>
@Module
class MigrationsModule {

    @Provides
    @IntoMap
    @IntKey(0)
    fun provideVersion1Migration(): VersionMigration {
        return Version0Migration()
    }
}