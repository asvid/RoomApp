package asvid.github.io.roomapp.model

import android.util.Log
import asvid.github.io.roomapp.api.pojo.Gist
import asvid.github.io.roomapp.api.pojo.Owner
import asvid.github.io.roomapp.data.gist.GistEntity
import asvid.github.io.roomapp.data.gistwithowner.GistWithOwner
import asvid.github.io.roomapp.data.owner.OwnerEntity

fun Gist.toModel(): GistModel {
  return GistModel(null, this.id, this.description ?: "", this.comments ?: "", this.url,
      this.owner.toModel(), false)
}

fun GistModel.toEntity(): GistEntity {
  val entity = GistEntity(this.gistId, this.description, this.comments, this.url,
      this.owner.id!!, this.starred)
  this.id.let { entity.id = it }
  return entity
}

fun Owner.toModel(): OwnerModel {
  return OwnerModel(this.login, this.url, this.avatarUrl, this.id.toLong())
}

fun OwnerModel.toEntity(): OwnerEntity {
  val entity = OwnerEntity(this.login ?: "", this.avatarUrl ?: "", this.ownerId,
      this.url ?: "")
  this.id.let { entity.dbId = it }
  return entity
}

fun GistWithOwner.toModel(): GistModel {
  Log.d("GistWithOwner", "toModel: $this")
  return GistModel(this.gist.id!!, this.gist.gistId, this.gist.description, this.gist.comments,
      this.gist.url,
      this.owner.toModel(), this.gist.starred)
}

fun List<GistWithOwner>.toModel(): List<GistModel> {
  return this.map { it.toModel() }
}

fun List<GistEntity>.convertToModel(): List<GistModel> {
  return this.map { it.toModel() }
}

private fun GistEntity.toModel(): GistModel {
  return GistModel(this.id, this.gistId, this.description, this.comments,
      this.url, OwnerModel(), this.starred)
}

fun OwnerEntity.toModel(): OwnerModel {
  return OwnerModel(this.login, this.ownerUrl, this.avatarUrl, this.gitOwnerId, this.dbId)
}

fun Collection<OwnerEntity>.toModel(): Collection<OwnerModel> {
  return this.map { it.toModel() }
}