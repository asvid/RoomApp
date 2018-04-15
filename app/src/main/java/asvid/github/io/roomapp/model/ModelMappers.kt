package asvid.github.io.roomapp.model

import asvid.github.io.roomapp.api.pojo.Gist
import asvid.github.io.roomapp.api.pojo.Owner
import asvid.github.io.roomapp.data.gist.GistEntity
import asvid.github.io.roomapp.data.gist.GistWithOwner
import asvid.github.io.roomapp.data.owner.OwnerEntity

fun Gist.toModel(): GistModel {
  return GistModel(this.id, this.description, this.comments, this.url,
      this.owner.toModel(), false)
}

fun GistModel.toEntity(): GistEntity {
  return GistEntity(this.id, this.description, this.comments, this.url, this.owner.id ?: -1L,
      this.starred)
}

fun Owner.toModel(): OwnerModel {
  return OwnerModel(this.login, this.url, this.avatarUrl, this.id?.toLong())
}

fun GistWithOwner.toModel(): GistModel {
  return GistModel(this.gist.id.toString(), this.gist.description, this.gist.comments,
      this.gist.url,
      this.owner[0].toModel(), this.gist.starred)
}

fun List<GistWithOwner>.toModel(): List<GistModel> {
  return this.map { it.toModel() }
}

fun List<GistEntity>.convertToModel(): List<GistModel> {
  return this.map { it.toModel() }
}

private fun GistEntity.toModel(): GistModel {
  return GistModel(this.id.toString(), this.description, this.comments,
      this.url,
      OwnerModel(), this.starred)
}

fun OwnerEntity.toModel(): OwnerModel {
  return OwnerModel(this.login, this.url, this.avatarUrl, this.ownerId.toLong())
}