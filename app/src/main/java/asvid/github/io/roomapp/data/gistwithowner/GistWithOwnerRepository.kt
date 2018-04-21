package asvid.github.io.roomapp.data.gistwithowner

import asvid.github.io.roomapp.model.GistModel
import asvid.github.io.roomapp.model.GistWithOwnerModel
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject

class GistWithOwnerRepository @Inject constructor(var gistWithOwnerDao: GistWithOwnerDao)
{
  fun delete(model: GistWithOwner): Completable {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  fun deleteAll(models: Collection<GistWithOwner>): Completable {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  fun fetchAll(): Flowable<Collection<GistWithOwner>> {
    return gistWithOwnerDao.getGistsWithOwners().map { it }
  }

  fun fetchById(id: Long): Maybe<GistWithOwner> {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  fun save(model: GistWithOwnerModel): Single<GistWithOwner> {
    return gistWithOwnerDao.saveOwnerAndGist(model.owner, model)
  }

  fun saveAll(models: Collection<GistWithOwner>): Single<Collection<GistWithOwner>> {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

}