package asvid.github.io.roomapp.data.owner

import asvid.github.io.roomapp.data.gist.Gist
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.Ignore
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import io.realm.annotations.Required

@RealmClass
open class Owner(
        @PrimaryKey
        @Required
        var id: Long? = null,
        @Required
        var login: String = "",
        var avatarUrl: String = "",

        var gists: RealmList<Gist>? = null,

        @Ignore
        var gistsNumber: Int = gists?.size ?: 0)
    : RealmObject()