package asvid.github.io.roomapp.data.mappers

import asvid.github.io.roomapp.data.owner.Owner
import asvid.github.io.roomapp.model.OwnerModel
import io.realm.Realm
import io.realm.RealmList

fun OwnerModel.toRealmModel(realm: Realm): Owner {
    val owner = realm.createObject(Owner::class.java, this.id)
    owner.login = this.login
    owner.gists = RealmList(*this.gists.map { it.toRealmModel(realm) }.toTypedArray())

    return owner
}

fun Owner.toModel(): OwnerModel {
    val ownerModel = OwnerModel(this.id, this.login)
    ownerModel.gists = this.gists?.map { it.toModel() } ?: listOf()
    return ownerModel
}

fun Owner.toSimpleModel(): OwnerModel {
    return OwnerModel(this.id, this.login)
}
