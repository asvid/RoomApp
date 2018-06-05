package asvid.github.io.roomapp.data.mappers

import asvid.github.io.roomapp.data.gist.Gist
import asvid.github.io.roomapp.data.gist.GistFields
import asvid.github.io.roomapp.model.GistModel
import io.realm.Realm

fun GistModel.toRealmModel(realm: Realm): Gist {

    if (this.id == null) {
        val gist = realm
                .use {
                    var maxId = it.where(Gist::class.java).max(GistFields.ID)?.toLong()
                    if (maxId == null) maxId = 0
                    it.createObject(Gist::class.java, ++maxId)
                }

        gist.description = this.description
        gist.starred = this.starred
        gist.date = this.date

        return gist
    } else {
        val gist = realm
                .use {
                    it.where(Gist::class.java)
                            .equalTo(GistFields.ID, this.id)
                            .findFirstAsync()
                }

        gist.description = this.description
        gist.starred = this.starred
        gist.date = this.date

        return gist
    }
}

fun Gist.toModel(): GistModel {
    return GistModel(this.id, this.description, this.owner!!.first()!!.toSimpleModel(), this.starred, this.date)
}