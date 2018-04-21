package asvid.github.io.roomapp.data.owner

import android.util.Log
import asvid.github.io.roomapp.data.repository.RxCrudRepository
import asvid.github.io.roomapp.model.OwnerModel
import asvid.github.io.roomapp.model.toEntity
import asvid.github.io.roomapp.model.toModel
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject

class OwnerRepository @Inject constructor(private var ownerDao: OwnerDao) :
    RxCrudRepository<OwnerModel, Long> {

  override fun delete(model: OwnerModel): Completable {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun deleteAll(models: Collection<OwnerModel>): Completable {
    return Completable.fromAction { ownerDao.delete() }
  }

  override fun fetchAll(): Flowable<Collection<OwnerModel>> {
    return ownerDao.getAllOwners().map { it.toModel() }
  }

  override fun fetchById(id: Long): Maybe<OwnerModel> {
    return ownerDao.findOwnerById(id).map { it.toModel() }
  }

  override fun save(model: OwnerModel): Single<OwnerModel> {
    Log.d("GIST_REPO", "save: $model")
    return Single.fromCallable {
      val id = ownerDao.insert(model.toEntity())
      model.id = id
      model

    }
  }

  override fun saveAll(models: Collection<OwnerModel>): Single<Collection<OwnerModel>> {
    return Single.fromCallable {
      models.map {
        it.id = ownerDao.insert(it.toEntity())
      }
      return@fromCallable fetchAll()
    }
  }

}