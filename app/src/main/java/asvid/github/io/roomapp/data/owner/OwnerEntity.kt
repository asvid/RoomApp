package asvid.github.io.roomapp.data.owner

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull
import asvid.github.io.roomapp.data.owner.OwnerEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class OwnerEntity(
    val login: String,
    val avatarUrl: String,
    val gitOwnerId: Long,
    val ownerUrl: String) {

  @PrimaryKey(autoGenerate = true)
  @NonNull
  var dbId: Long? = null

  companion object {
    const val TABLE_NAME = "owner"
    const val ID = "dbId"
  }
}