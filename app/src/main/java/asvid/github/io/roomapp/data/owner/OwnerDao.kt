package asvid.github.io.roomapp.data.owner

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import io.reactivex.Flowable
import io.reactivex.Maybe

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

    @Query("SELECT * FROM ${OwnerEntity.TABLE_NAME} WHERE ${OwnerEntity.ID}=:ownerId")
    fun findOwnerById(ownerId: Long): Maybe<OwnerEntity>
}