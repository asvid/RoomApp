package asvid.github.io.roomapp.data.owner

import asvid.github.io.roomapp.data.mappers.toModel
import asvid.github.io.roomapp.data.mappers.toRealmModel
import asvid.github.io.roomapp.data.repository.RxCrudRepository
import asvid.github.io.roomapp.model.OwnerModel
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmQuery
import timber.log.Timber
import javax.inject.Inject

class OwnerRepository @Inject constructor(private val realmConfiguration: RealmConfiguration) : RxCrudRepository<OwnerModel, Long> {

    override fun delete(model: OwnerModel): Completable {
        return Completable.fromAction {
            Realm.getInstance(realmConfiguration).use {
                it.executeTransactionAsync {
                    val ownerToDelete = it.where(Owner::class.java)
                            .equalTo(OwnerFields.ID, model.id)
                            .findFirstAsync()
                    ownerToDelete.gists?.deleteAllFromRealm()
                    ownerToDelete.deleteFromRealm()
                }
            }
        }
    }

    override fun deleteAll(models: Collection<OwnerModel>): Completable {
        return Completable.fromAction {
            Realm.getInstance(realmConfiguration).executeTransaction {
                it.where(Owner::class.java).findAllAsync().deleteAllFromRealm()
            }
        }
    }

    override fun fetchAll(): Flowable<Collection<OwnerModel>> {
        return Realm.getInstance(realmConfiguration)
                .where(Owner::class.java)
                .findAll()
                .asFlowable()
                .map {
                    it.map {
                        it.toModel()
                    }
                }
    }


    fun fetchAllOnce(): Single<Collection<OwnerModel>> {
        return Single.create<Collection<OwnerModel>> {
            val realm = Realm.getInstance(realmConfiguration)
            it.onSuccess(realm.where(Owner::class.java)
                    .findAll()
                    .map {
                        it.toModel()
                    })
            realm.close()
        }
    }

    private fun <T> realmAction(block: RealmQuery<Owner>.() -> T): T {
        return Realm.getInstance(realmConfiguration).use {
            return@use it.where(Owner::class.java)
                    .block()
        }
    }

    override fun fetchById(id: Long): Maybe<OwnerModel> {
        return Maybe.fromAction { realmAction { equalTo(OwnerFields.ID, id).findFirstAsync() } }
    }

    override fun save(model: OwnerModel): Single<OwnerModel> {
        return Single.create<OwnerModel> {
            val realm = Realm.getInstance(realmConfiguration)
            realm.executeTransaction {
                if (model.id == null) {
                    var maxId = it.where(Owner::class.java).max(OwnerFields.ID)?.toLong()
                    Timber.d("maxId: $maxId")
                    if (maxId == null) maxId = 0
                    model.id = ++maxId
                }
                Timber.d("owner: $model")
                it.copyToRealmOrUpdate(model.toRealmModel(it))
            }
            it.onSuccess(model)
            realm.close()

        }
    }

    override fun saveAll(models: Collection<OwnerModel>): Single<Collection<OwnerModel>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}