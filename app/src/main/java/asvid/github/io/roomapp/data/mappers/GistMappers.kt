package asvid.github.io.roomapp.data.mappers

import asvid.github.io.roomapp.data.gist.Gist
import asvid.github.io.roomapp.data.owner.Owner
import asvid.github.io.roomapp.model.GistModel
import io.realm.RealmResults

fun GistModel.toRealmModel(): Gist {
    return Gist(this.id, this.description, RealmResults<Owner>() this.owner.toRealmModel(), this.starred, this.date)
}

fun Gist.toModel(): GistModel {
    return GistModel(this.id, this.description, this.owner!!.first()!!.toModel(), this.starred, this.date)
}