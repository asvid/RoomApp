package asvid.github.io.roomapp.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import asvid.github.io.roomapp.data.gist.GistDao
import asvid.github.io.roomapp.data.gist.GistEntity
import asvid.github.io.roomapp.data.gistwithowner.GistWithOwnerDao
import asvid.github.io.roomapp.data.owner.OwnerDao
import asvid.github.io.roomapp.data.owner.OwnerEntity
import asvid.github.io.roomapp.data.ownerwithgists.OwnerWithGistsDao

@Database(entities = [
  GistEntity::class,
  OwnerEntity::class
], version = 1)
abstract class GistDatabase : RoomDatabase() {

  abstract fun gistDao(): GistDao
  abstract fun ownerDao(): OwnerDao
  abstract fun gistWithOwnerDao(): GistWithOwnerDao
  abstract fun ownerWithGistDao(): OwnerWithGistsDao
}