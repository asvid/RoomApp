package asvid.github.io.roomapp.model

import android.util.Log
import asvid.github.io.roomapp.data.gist.GistEntity
import asvid.github.io.roomapp.data.gistwithowner.GistWithOwner
import asvid.github.io.roomapp.data.owner.OwnerEntity

fun GistModel.toEntity(): GistEntity {
  val entity = GistEntity(this.description, this.owner.id!!, this.starred)
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
      this.gist.starred)
}

fun List<GistEntity>.convertToModel(): List<GistModel> {
  return this.map { it.toModel() }
}

private fun GistEntity.toModel(): GistModel {
  return GistModel(this.id, this.description, this.ownerId, this.starred)
}

fun OwnerEntity.toModel(): OwnerModel {
  return OwnerModel(this.login, this.id)
}

fun Collection<OwnerEntity>.toModel(): Collection<OwnerModel> {
  return this.map { it.toModel() }
}