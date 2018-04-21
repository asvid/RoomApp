package asvid.github.io.roomapp.data.gistwithowner

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Query
import android.arch.persistence.room.Transaction
import asvid.github.io.roomapp.data.gist.GistDao
import asvid.github.io.roomapp.data.gist.GistEntity
import asvid.github.io.roomapp.data.owner.OwnerDao
import asvid.github.io.roomapp.data.owner.OwnerEntity
import asvid.github.io.roomapp.model.GistModel
import asvid.github.io.roomapp.model.GistWithOwnerModel
import asvid.github.io.roomapp.model.OwnerModel
import asvid.github.io.roomapp.model.toEntity
import io.reactivex.Flowable
import io.reactivex.Single

class GistWithOwner {
  @Embedded
  lateinit var gist: GistEntity
  @Embedded
  lateinit var owner: OwnerEntity

  override fun toString(): String {
    return "gist: $gist | owner: $owner"
  }
}

@Dao
interface GistWithOwnerDao : GistDao, OwnerDao {

  @Query(
      "SELECT * from ${GistEntity.TABLE_NAME}, ${OwnerEntity.TABLE_NAME} where gist.ownerId = owner.dbId")
  fun getGistsWithOwners(): Flowable<List<GistWithOwner>>

  @Transaction
  fun saveOwnerAndGist(owner: OwnerModel, gist: GistModel): Single<GistWithOwnerModel> {
    return Single.fromCallable {
      val ownerId = insert(owner.toEntity())
      gist.ownerId = ownerId
      insert(gist.toEntity())
      GistWithOwnerModel(gist.id, gist.description, owner, gist.starred)
    }
  }

  @Transaction
  fun saveOwnerAndGist(gistWithOwnerModel: GistWithOwnerModel) {
    val ownerId = insert(gistWithOwnerModel.owner.toEntity())
    val gist = GistModel(gistWithOwnerModel.id, gistWithOwnerModel.description, ownerId,
        gistWithOwnerModel.starred)
    insert(gist.toEntity())
  }

}