package asvid.github.io.roomapp.data.owner

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull
import asvid.github.io.roomapp.data.owner.OwnerEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class OwnerEntity(
        val login: String) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "owner_id")
    @NonNull
    var id: Long? = null

    companion object {
        const val TABLE_NAME = "owner"
        const val ID = "owner_id"
    }
}