package asvid.github.io.roomapp.data.migration

import dagger.Reusable
import io.realm.DynamicRealm
import io.realm.RealmMigration
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Provider

// TODO(16) główny obiekt migracji, który wykorzystuje wstrzykniętą listę migracji z konkretnych wersji
@Reusable
class Migration @Inject constructor(private val versionMigrations: Map<Int, Provider<VersionMigration>>) : RealmMigration {

    override fun migrate(realm: DynamicRealm, oldVersion: Long, newVersion: Long) {
        Timber.d("migrating from $oldVersion to $newVersion")
//         TODO(17) iteracja po wszystkich brakujących migracjach od wersji OLD do NEW
        for (i in oldVersion.toInt() until newVersion) {
            val provider = versionMigrations[i.toInt()]
            Timber.d("migration $i")
//            TODO(18) uruchomienie konkretnej migracji
            provider?.get()?.migrate(realm, i)
        }
    }
}
