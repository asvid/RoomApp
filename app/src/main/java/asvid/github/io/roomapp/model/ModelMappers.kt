package asvid.github.io.roomapp.model

import android.util.Log
import asvid.github.io.roomapp.data.gist.GistEntity
import asvid.github.io.roomapp.data.gistwithowner.GistWithOwner
import asvid.github.io.roomapp.data.owner.OwnerEntity
import asvid.github.io.roomapp.data.ownerwithgists.OwnerWithGists

fun GistModel.toEntity(): GistEntity {
    val entity = GistEntity(this.description, this.ownerId, this.starred, this.date)
    this.id.let { entity.id = it }
    return entity
}

fun OwnerModel.toEntity(): OwnerEntity {
    val entity = OwnerEntity(this.login ?: "")
    this.id.let { entity.id = it }
    return entity
}

fun GistWithOwner.toModel(): GistWithOwnerModel {
    Log.d("GistWithOwner", "toModel: $this")
    return GistWithOwnerModel(this.gist.id!!, this.gist.description, this.owner.toModel(),
            this.gist.starred, this.gist.date)
}

fun List<GistEntity>.convertToModel(): List<GistModel> {
    return this.map { it.toModel() }
}

fun List<GistWithOwner>.toModel(): List<GistWithOwnerModel> {
    return this.map { it.toModel() }
}

private fun GistEntity.toModel(): GistModel {
    return GistModel(this.id, this.description, this.ownerId, this.starred, this.date)
}

fun OwnerEntity.toModel(): OwnerModel {
    return OwnerModel(this.login, this.id)
}

fun Collection<OwnerEntity>.toModel(): Collection<OwnerModel> {
    return this.map { it.toModel() }
}

fun OwnerWithGists.toModel(): OwnerWithGistsModel {
    return OwnerWithGistsModel(this.owner.login, this.owner.id, this.gists.convertToModel())
}

fun List<OwnerWithGists>.model(): List<OwnerWithGistsModel> {
    return this.map { it.toModel() }
}