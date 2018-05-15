package asvid.github.io.roomapp.data.gist

import asvid.github.io.roomapp.data.owner.Owner
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import java.util.*

@RealmClass
open class Gist(
        @PrimaryKey
        var id: Long,
        val description: String,
        val owner: Owner,
        val starred: Boolean,
        var date: Date
) : RealmObject()