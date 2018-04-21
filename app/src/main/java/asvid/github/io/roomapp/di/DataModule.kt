package asvid.github.io.roomapp.di

import android.arch.persistence.room.Room
import android.content.Context
import asvid.github.io.roomapp.data.GistDatabase
import asvid.github.io.roomapp.data.gist.GistDao
import asvid.github.io.roomapp.data.gist.GistRepository
import asvid.github.io.roomapp.data.gistwithowner.GistWithOwnerDao
import asvid.github.io.roomapp.data.gistwithowner.GistWithOwnerRepository
import asvid.github.io.roomapp.data.owner.OwnerDao
import asvid.github.io.roomapp.data.owner.OwnerRepository
import asvid.github.io.roomapp.data.ownerwithgists.OwnerWithGistsDao
import asvid.github.io.roomapp.data.ownerwithgists.OwnerWithGistsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [AppModule::class])
class DataModule {
  @Singleton
  @Provides
  fun gistDatabase(context: Context): GistDatabase = Room.databaseBuilder(context,
      GistDatabase::class.java, "Gist.db").allowMainThreadQueries().build()

  @Singleton
  @Provides
  fun gistRepository(counterDao: GistDao): GistRepository = GistRepository(counterDao)

  @Singleton
  @Provides
  fun gistDao(context: Context): GistDao = gistDatabase(context).gistDao()

  @Singleton
  @Provides
  fun ownerRepository(ownerDao: OwnerDao): OwnerRepository = OwnerRepository(ownerDao)

  @Singleton
  @Provides
  fun ownerDao(context: Context): OwnerDao = gistDatabase(context).ownerDao()

  @Singleton
  @Provides
  fun gistWithOwnerRepository(
      ownerDao: GistWithOwnerDao): GistWithOwnerRepository = GistWithOwnerRepository(
      ownerDao)

  @Singleton
  @Provides
  fun gistWithOwnerDao(context: Context): GistWithOwnerDao = gistDatabase(
      context).gistWithOwnerDao()

  @Singleton
  @Provides
  fun ownerWithGistsRepository(
      ownerWithGistsDao: OwnerWithGistsDao): OwnerWithGistsRepository = OwnerWithGistsRepository(
      ownerWithGistsDao)

  @Singleton
  @Provides
  fun ownerWithGistsDao(context: Context): OwnerWithGistsDao = gistDatabase(
      context).ownerWithGistDao()
}