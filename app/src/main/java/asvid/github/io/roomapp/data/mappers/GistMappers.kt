package asvid.github.io.roomapp.data.mappers

import asvid.github.io.roomapp.data.gist.Gist
import asvid.github.io.roomapp.model.GistModel
import io.realm.Realm

fun GistModel.toRealmModel(): Gist {
    val gist = Realm.getDefaultInstance().createObject(Gist::class.java, this.id)

    gist.description = this.description
    gist.starred = this.starred
    gist.date = this.date

    return gist
}

fun Gist.toModel(): GistModel {
    return GistModel(this.id, this.description, this.owner!!.first()!!.toSimpleModel(), this.starred, this.date)
}