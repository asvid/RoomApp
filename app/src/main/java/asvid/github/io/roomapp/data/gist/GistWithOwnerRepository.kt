package asvid.github.io.roomapp.data.gist

import asvid.github.io.roomapp.data.repository.RxCrudRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject

class GistWithOwnerRepository @Inject constructor(var gistWithOwnerDao: GistWithOwnerDao) :
    RxCrudRepository<GistWithOwner, Long> {
  override fun delete(model: GistWithOwner): Completable {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun deleteAll(models: Collection<GistWithOwner>): Completable {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun fetchAll(): Flowable<Collection<GistWithOwner>> {
    return Flowable.fromCallable { gistWithOwnerDao.getGistsWithOwners() }
  }

  override fun fetchById(id: Long): Maybe<GistWithOwner> {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun save(model: GistWithOwner): Single<GistWithOwner> {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun saveAll(models: Collection<GistWithOwner>): Single<Collection<GistWithOwner>> {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

}