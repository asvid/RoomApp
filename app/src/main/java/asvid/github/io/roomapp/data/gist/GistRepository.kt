package asvid.github.io.roomapp.data.gist

import asvid.github.io.roomapp.data.mappers.toModel
import asvid.github.io.roomapp.data.mappers.toRealmModel
import asvid.github.io.roomapp.data.owner.Owner
import asvid.github.io.roomapp.data.owner.OwnerFields
import asvid.github.io.roomapp.data.repository.RxCrudRepository
import asvid.github.io.roomapp.model.GistModel
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmQuery
import javax.inject.Inject

class GistRepository @Inject constructor(private val realmConfiguration: RealmConfiguration) : RxCrudRepository<GistModel, Long> {

    override fun delete(model: GistModel): Completable {
        return Completable.fromAction {
            Realm.getInstance(realmConfiguration).use {
                it.where(Gist::class.java).equalTo(GistFields.ID, model.id).findFirstAsync().deleteFromRealm()
            }
        }
    }

    override fun deleteAll(models: Collection<GistModel>): Completable {
        return Completable.fromAction {
            Realm.getInstance(realmConfiguration).use {
                it.executeTransaction {
                    it.where(Gist::class.java).findAll().deleteAllFromRealm()
                }
            }
        }
    }

    private fun <T> realmAction(block: RealmQuery<Gist>.() -> T): T {
        return Realm.getInstance(realmConfiguration).use {
            return@use it.where(Gist::class.java)
                    .block()
        }
    }

    override fun fetchAll(): Flowable<Collection<GistModel>> {
        return Realm.getInstance(realmConfiguration)
                .where(Gist::class.java)
                .findAll()
                .asFlowable()
                .map {
                    it.map {
                        it.toModel()
                    }
                }

    }


    override fun fetchById(id: Long): Maybe<GistModel> {
        return Maybe.fromAction {
            realmAction { equalTo(GistFields.ID, id).findFirstAsync() }
        }
    }

    override fun save(model: GistModel): Single<GistModel> {
        return Single.fromCallable {
            Realm.getInstance(realmConfiguration).use {
                it.executeTransaction {
                    val gist = model.toRealmModel(it)
                    val owner = it.where(Owner::class.java).equalTo(OwnerFields.ID, model.owner?.id).findFirst()
                    owner?.gists?.add(gist)
                    it.copyToRealmOrUpdate(owner)
                    model.id = gist.id
                }
            }
            model
        }
    }

    override fun saveAll(models: Collection<GistModel>): Single<Collection<GistModel>> {
        TODO(
                "not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun update(model: GistModel): Single<GistModel> {
        return Single.fromCallable {
            Realm.getInstance(realmConfiguration).use {
                it.executeTransaction {
                    it.insertOrUpdate(model.toRealmModel(it))
                }
            }
            model
        }
    }
}