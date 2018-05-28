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
import io.realm.RealmQuery
import timber.log.Timber
import javax.inject.Inject

class OwnerRepository @Inject constructor(val realm: Realm) :
        RxCrudRepository<OwnerModel, Owner, Long> {

    override val repositoryDb: RealmQuery<Owner> = realm.where(Owner::class.java)

    override fun delete(model: OwnerModel): Completable {
        return Completable.fromAction {
            Realm.getDefaultInstance().executeTransaction {
                val ownerToDelete = it.where(Owner::class.java)
                        .equalTo("id", model.id)
                        .findFirstAsync()
                ownerToDelete.gists.deleteAllFromRealm()
                ownerToDelete.deleteFromRealm()
            }
        }
    }

    override fun deleteAll(models: Collection<OwnerModel>): Completable {
        return Completable.fromAction {
            Realm.getDefaultInstance().executeTransaction {
                it.where(Owner::class.java).findAll().deleteAllFromRealm()
            }
        }
    }

    override fun fetchAll(): Flowable<Collection<OwnerModel>> {
        return repositoryDb.findAll()
                .asFlowable().map { it.map { it.toModel() } }
    }

    override fun fetchById(id: Long): Maybe<OwnerModel> {
        return Maybe.fromAction { repositoryDb.equalTo("id", id).findFirstAsync() }
    }

    override fun save(model: OwnerModel): Single<OwnerModel> {
        return Single.fromCallable {
            Realm.getDefaultInstance().executeTransactionAsync {
                if (model.id == null) {
                    var maxId = it.where(Owner::class.java).max("id")?.toLong()
                    Timber.d("maxId: $maxId")
                    if (maxId == null) maxId = 0
                    model.id = ++maxId
                }
                Timber.d("owner: $model")
                it.copyToRealmOrUpdate(model.toRealmModel())

            }
            model
        }
    }

    override fun saveAll(models: Collection<OwnerModel>): Single<Collection<OwnerModel>> {

        TODO(
                "not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}