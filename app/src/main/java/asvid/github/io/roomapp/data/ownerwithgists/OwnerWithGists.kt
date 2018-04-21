package asvid.github.io.roomapp.data.ownerwithgists

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation
import asvid.github.io.roomapp.data.gist.GistEntity
import asvid.github.io.roomapp.data.owner.OwnerEntity

class OwnerWithGists {

  @Embedded
  lateinit var owner: OwnerEntity

  @Relation(parentColumn = "id", entityColumn = "ownerId", entity = GistEntity::class)
  lateinit var gists: List<GistEntity>
}