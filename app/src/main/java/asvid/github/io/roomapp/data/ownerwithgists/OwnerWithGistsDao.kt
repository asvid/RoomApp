package asvid.github.io.roomapp.data.ownerwithgists

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import asvid.github.io.roomapp.data.owner.OwnerEntity
import io.reactivex.Flowable

@Dao
interface OwnerWithGistsDao {

  @Query("SELECT * from ${OwnerEntity.TABLE_NAME}")
  fun getOwnersWithGists(): Flowable<List<OwnerWithGists>>

  @Query("SELECT * from ${OwnerEntity.TABLE_NAME} where id=:ownerId")
  fun getOwnerByIdWithGists(ownerId: Long): Flowable<List<OwnerWithGists>>

}