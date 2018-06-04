package asvid.github.io.roomapp.data.migration

import dagger.Reusable
import io.realm.DynamicRealm
import io.realm.RealmMigration
import javax.inject.Inject
import javax.inject.Provider

@Reusable
class Migration @Inject constructor(private val versionMigrations: Map<Int, Provider<VersionMigration>>) : RealmMigration {

    override fun migrate(realm: DynamicRealm, oldVersion: Long, newVersion: Long) {
        for (i in oldVersion.toInt() until newVersion) {
            val provider = versionMigrations[i.toInt()]
            provider?.get()?.migrate(realm, i)
        }
    }
}
