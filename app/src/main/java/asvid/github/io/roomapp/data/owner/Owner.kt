package asvid.github.io.roomapp.data.owner

import asvid.github.io.roomapp.data.gist.Gist
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.*

@RealmClass
open class Owner(
        @PrimaryKey
        @Required
        var id: Long? = null,
        @Required
        var login: String = "",

        var gists: RealmList<Gist> = RealmList(),

        @Ignore
        var gistsNumber: Int = gists?.size ?: 0)
    : RealmObject()