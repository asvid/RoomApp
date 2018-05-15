package asvid.github.io.roomapp.data.mappers

import asvid.github.io.roomapp.data.gist.Gist
import asvid.github.io.roomapp.model.GistModel

fun GistModel.toRoomModel(): Gist{
    return Gist(this.id, this.description, this.)
}

fun Gist.toModel(): GistModel{
    return GistModel(this.id, this.description, this.owner.toModel(), this.starred, this.date)
}