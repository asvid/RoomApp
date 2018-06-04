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
import javax.inject.Inject

class GistRepository @Inject constructor(val realm: Realm) : RxCrudRepository<GistModel, Long> {

    override fun delete(model: GistModel): Completable {
        return Completable.fromAction {
            realm.use {
                it.where(Gist::class.java).equalTo(GistFields.ID, model.id).findFirstAsync().deleteFromRealm()
            }
        }
    }

    override fun deleteAll(models: Collection<GistModel>): Completable {
        return Completable.fromAction {
            realm.use {
                it.executeTransaction {
                    it.where(Gist::class.java).findAll().deleteAllFromRealm()
                }
            }
        }
    }

    private fun getGistRealm() = realm.where(Gist::class.java)

    override fun fetchAll(): Flowable<Collection<GistModel>> {
        return getGistRealm().findAll()
                .let {
                    it.asFlowable().map {
                        it.map {
                            it.toModel()
                        }
                    }
                }
    }

    override fun fetchById(id: Long): Maybe<GistModel> {
        return Maybe.fromAction { getGistRealm().equalTo(GistFields.ID, id).findFirstAsync() }
    }

    override fun save(model: GistModel): Single<GistModel> {
        return Single.fromCallable {
            realm.use {
                it.refresh()
                it.executeTransaction {
                    val gist = model.toRealmModel()
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
            realm.use {
                it.executeTransaction {
                    it.insertOrUpdate(model.toRealmModel())
                }
            }
            model
        }
    }
}