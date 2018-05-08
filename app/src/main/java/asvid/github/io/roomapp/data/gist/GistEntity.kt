package asvid.github.io.roomapp.data.gist

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.ForeignKey.CASCADE
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import android.support.annotation.NonNull
import asvid.github.io.roomapp.data.gist.GistEntity.Companion.TABLE_NAME
import asvid.github.io.roomapp.data.owner.OwnerEntity
import asvid.github.io.roomapp.data.typeconverters.DateTypeConverter
import java.util.*

@Entity(tableName = TABLE_NAME, foreignKeys = [
    (ForeignKey(
            entity = OwnerEntity::class,
            parentColumns = [(OwnerEntity.ID)],
            childColumns = [(GistEntity.OWNER_ID)],
            onDelete = CASCADE,
            onUpdate = CASCADE))])

@TypeConverters(DateTypeConverter::class)
data class GistEntity(
        val description: String,
        @NonNull val ownerId: Long,
        val starred: Boolean,
        var date: Date) {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    var id: Long? = null

    companion object {
        const val TABLE_NAME = "gist"
        const val OWNER_ID = "ownerId"
    }
}