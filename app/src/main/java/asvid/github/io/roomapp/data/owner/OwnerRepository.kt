package asvid.github.io.roomapp.data.owner

import android.util.Log
import asvid.github.io.roomapp.data.repository.RxCrudRepository
import asvid.github.io.roomapp.model.OwnerModel
import asvid.github.io.roomapp.model.toEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import io.realm.RealmQuery
import javax.inject.Inject

class OwnerRepository @Inject constructor(override val gistDatabase: RealmQuery<Owner>) :
        RxCrudRepository<OwnerModel, Owner, Long> {

    override fun delete(model: OwnerModel): Completable {
        return Completable.fromAction {
            gistDatabase.equalTo("id", model.id).findFirst()?.deleteFromRealm()
        }
    }

    override fun deleteAll(models: Collection<OwnerModel>): Completable {
        return Completable.fromAction { realmDb.findAll().deleteAllFromRealm() }
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
                val id = ownerDao.insert(it.toEntity())
                it.id = id
            }
            models
        }
    }

}