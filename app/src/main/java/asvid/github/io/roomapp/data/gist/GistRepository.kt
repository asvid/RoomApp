package asvid.github.io.roomapp.data.gist

import android.util.Log
import asvid.github.io.roomapp.data.repository.RxCrudRepository
import asvid.github.io.roomapp.model.GistModel
import asvid.github.io.roomapp.model.convertToModel
import asvid.github.io.roomapp.model.toEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject

class GistRepository @Inject constructor(var gistDao: GistDao) :
    RxCrudRepository<GistModel, Long> {
  override fun delete(model: GistModel): Completable {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun deleteAll(models: Collection<GistModel>): Completable {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun fetchAll(): Flowable<Collection<GistModel>> {
    return gistDao.getAllGists().map { it.convertToModel() }
  }

  override fun fetchById(id: Long): Maybe<GistModel> {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun save(model: GistModel): Single<GistModel> {
    Log.d("GIST_REPO", "save: $model")
    return Single.fromCallable {
      val id = gistDao.insert(model.toEntity())
      model.id = id
      model
    }
  }

  override fun saveAll(models: Collection<GistModel>): Single<Collection<GistModel>> {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}