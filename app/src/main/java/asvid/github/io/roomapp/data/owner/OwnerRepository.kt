package asvid.github.io.roomapp.data.owner

import asvid.github.io.roomapp.data.repository.RxCrudRepository
import asvid.github.io.roomapp.model.OwnerModel
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject

class OwnerRepository @Inject constructor(var gistDao: OwnerDao) :
    RxCrudRepository<OwnerModel, Long> {
  override fun delete(model: OwnerModel): Completable {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun deleteAll(models: Collection<OwnerModel>): Completable {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun fetchAll(): Flowable<Collection<OwnerModel>> {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun fetchById(id: Long): Maybe<OwnerModel> {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun save(model: OwnerModel): Single<OwnerModel> {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun saveAll(models: Collection<OwnerModel>): Single<Collection<OwnerModel>> {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

}