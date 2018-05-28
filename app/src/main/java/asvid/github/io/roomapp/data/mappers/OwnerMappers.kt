package asvid.github.io.roomapp.data.mappers

import asvid.github.io.roomapp.data.gist.Gist
import asvid.github.io.roomapp.data.owner.Owner
import asvid.github.io.roomapp.model.OwnerModel
import io.realm.RealmList

fun OwnerModel.toRealmModel(): Owner {
    return Owner(this.id, this.login, RealmList<Gist>(*this.gists.map { it.toRealmModel() }.toTypedArray()))
}

fun Owner.toModel(): OwnerModel {
    return OwnerModel(this.id, this.login, this.gists.map { it.toModel() })
}
