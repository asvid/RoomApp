package asvid.github.io.roomapp.data.owner

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import io.reactivex.Flowable

@Dao
interface OwnerDao {

  @Insert(onConflict = REPLACE)
  fun insert(counterEntity: OwnerEntity): Long

  @Update
  fun update(vararg repos: OwnerEntity)

  @Delete
  fun delete(vararg repos: OwnerEntity)

  @Query("SELECT * FROM ${OwnerEntity.TABLE_NAME}")
  fun getAllOwners(): Flowable<List<OwnerEntity>>

  @Query("SELECT * FROM ${OwnerEntity.TABLE_NAME} WHERE id=:ownerId")
  fun findOwnerById(ownerId: Long): Flowable<OwnerEntity>
}