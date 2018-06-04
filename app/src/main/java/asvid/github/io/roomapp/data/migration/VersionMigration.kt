package asvid.github.io.roomapp.data.migration

import io.realm.DynamicRealm

interface VersionMigration {
    fun migrate(realm: DynamicRealm, oldVersion: Long)
}