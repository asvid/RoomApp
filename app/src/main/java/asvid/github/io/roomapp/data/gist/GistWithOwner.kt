package asvid.github.io.roomapp.data.gist

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Query
import android.arch.persistence.room.Relation
import asvid.github.io.roomapp.data.owner.OwnerEntity

class GistWithOwner {
  @Embedded
  lateinit var gist: GistEntity

  @Relation(parentColumn = "id", entityColumn = "ownerId", entity = OwnerEntity::class)
  lateinit var owner: List<OwnerEntity>
}

@Dao
interface GistWithOwnerDao {

  @Query("SELECT * from ${GistEntity.TABLE_NAME}")
  fun getGistsWithOwners(): List<GistWithOwner>
}