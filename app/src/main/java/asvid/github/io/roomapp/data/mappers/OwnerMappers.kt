package asvid.github.io.roomapp.data.mappers

import asvid.github.io.roomapp.data.gist.Gist
import asvid.github.io.roomapp.data.owner.Owner
import asvid.github.io.roomapp.model.GistModel
import asvid.github.io.roomapp.model.OwnerModel

fun OwnerModel.toRoomModel(): Owner {
    return Gist(this.id, this.description, this.)
}

fun Owner.toModel(): OwnerModel {
    return GistModel(this.id, this.description, this.owner.toModel(), this.starred, this.date)
}
