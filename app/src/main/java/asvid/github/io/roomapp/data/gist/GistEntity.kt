package asvid.github.io.roomapp.data.gist

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.ForeignKey.CASCADE
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull
import asvid.github.io.roomapp.data.gist.GistEntity.Companion.TABLE_NAME
import asvid.github.io.roomapp.data.owner.OwnerEntity

@Entity(tableName = TABLE_NAME, foreignKeys = [
  (ForeignKey(
      entity = OwnerEntity::class,
      parentColumns = [(OwnerEntity.ID)],
      childColumns = [(GistEntity.OWNER_ID)],
      onDelete = CASCADE))])
data class GistEntity(
    val gistId: String,
    val description: String,
    val comments: String,
    val url: String,
    @NonNull val ownerId: Long,
    val starred: Boolean) {

  @PrimaryKey(autoGenerate = true)
  @NonNull
  var id: Long? = null

  companion object {
    const val TABLE_NAME = "gist"
    const val OWNER_ID = "ownerId"
  }
}