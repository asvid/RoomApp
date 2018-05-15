package asvid.github.io.roomapp.data

import io.realm.Realm
import io.realm.RealmConfiguration


object GistDatabase {

    fun init() {
        val realmConfiguration = RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(realmConfiguration)
    }
}