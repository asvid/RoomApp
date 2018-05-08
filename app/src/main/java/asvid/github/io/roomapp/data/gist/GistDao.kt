package asvid.github.io.roomapp.data.gist

import android.arch.persistence.room.*
import asvid.github.io.roomapp.data.gist.GistEntity.Companion.TABLE_NAME
import io.reactivex.Flowable

@Dao
interface GistDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(counterEntity: GistEntity): Long

    @Update
    fun update(vararg repos: GistEntity)

    @Delete
    fun delete(vararg repos: GistEntity)

    @Query("SELECT * FROM $TABLE_NAME")
    fun getAllGists(): Flowable<List<GistEntity>>

    @Query("SELECT * FROM $TABLE_NAME WHERE ownerId=:ownerId")
    fun findGistsForOwner(ownerId: Long): Flowable<List<GistEntity>>
}