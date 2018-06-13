package asvid.github.io.roomapp.data.owner

import asvid.github.io.roomapp.data.gist.Gist
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.Ignore
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import io.realm.annotations.Required

// TODO(3) klasa Owner przechowywana w Realm
@RealmClass
open class Owner(
        @PrimaryKey
        @Required
        var id: Long? = null,
        @Required
        var login: String = "",
        var avatarUrl: String? = "",
// TODO(4) relacja z Gistami
        var gists: RealmList<Gist>? = null,
// TODO(5) ignorowane pole, które nie jest zapisywane w bazie
        @Ignore
        var gistsNumber: Int = gists?.size ?: 0)
    : RealmObject()