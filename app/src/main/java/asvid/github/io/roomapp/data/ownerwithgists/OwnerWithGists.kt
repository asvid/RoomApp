package asvid.github.io.roomapp.data.ownerwithgists

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Query
import android.arch.persistence.room.Relation
import asvid.github.io.roomapp.data.gist.GistEntity
import asvid.github.io.roomapp.data.owner.OwnerEntity
import io.reactivex.Flowable
import io.reactivex.Maybe

class OwnerWithGists {

  @Embedded
  lateinit var owner: OwnerEntity

  @Relation(parentColumn = "id", entityColumn = "ownerId", entity = GistEntity::class)
  lateinit var gists: List<GistEntity>
}

@Dao
interface OwnerWithGistsDao {

  @Query("SELECT * from ${OwnerEntity.TABLE_NAME}")
  fun getOwnersWithGists(): Flowable<List<OwnerWithGists>>

  @Query("SELECT * from ${OwnerEntity.TABLE_NAME} where id=:ownerId")
  fun getOwnerByIdWithGists(ownerId: Long): Maybe<OwnerWithGists>

}