package asvid.github.io.roomapp.data.owner

import asvid.github.io.roomapp.data.gist.Gist
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.Ignore
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class Owner(
        @PrimaryKey
        var id: Long?,
        val login: String,
        var gists: RealmList<Gist> = RealmList(),
        @Ignore var gistsNumber: Int = gists.size)
    : RealmObject()