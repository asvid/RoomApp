package asvid.github.io.roomapp.data.mappers

import asvid.github.io.roomapp.data.owner.Owner
import asvid.github.io.roomapp.model.OwnerModel
import io.realm.Realm
import io.realm.RealmList

fun OwnerModel.toRealmModel(): Owner {
    val owner = Realm.getDefaultInstance().createObject(Owner::class.java, this.id)
    owner.login = this.login
    owner.gists = RealmList(*this.gists.map { it.toRealmModel() }?.toTypedArray())

    return owner
}

fun Owner.toModel(): OwnerModel {
    return OwnerModel(this.id, this.login)
}
