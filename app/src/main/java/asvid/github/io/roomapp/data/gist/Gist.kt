package asvid.github.io.roomapp.data.gist

import asvid.github.io.roomapp.data.owner.Owner
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.LinkingObjects
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import io.realm.annotations.Required
import java.util.*

@RealmClass
open class Gist(
        @PrimaryKey
        @Required
        var id: Long? = null,
        var description: String = "",
        @LinkingObjects("gists")
        val owner: RealmResults<Owner>? = null,
        var starred: Boolean = false,
        var date: Date = Date()
) : RealmObject()