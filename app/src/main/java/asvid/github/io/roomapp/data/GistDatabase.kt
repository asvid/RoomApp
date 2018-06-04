package asvid.github.io.roomapp.data

import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration


object GistDatabase {

    fun init(context: Context) {
        Realm.init(context)
        val realmConfiguration = RealmConfiguration.Builder()
                .schemaVersion(0)
                .build()
        Realm.setDefaultConfiguration(realmConfiguration)
    }
}