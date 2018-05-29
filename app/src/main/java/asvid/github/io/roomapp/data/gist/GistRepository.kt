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
import timber.log.Timber

class GistRepository : RxCrudRepository<GistModel, Long> {

    override fun delete(model: GistModel): Completable {
        return Completable.fromAction {
            Realm.getDefaultInstance().use {
                it.where(Gist::class.java).equalTo(GistFields.ID, model.id).findFirstAsync().deleteFromRealm()
            }
        }
    }

    override fun deleteAll(models: Collection<GistModel>): Completable {
        return Completable.fromAction {
            Realm.getDefaultInstance().use {
                it.executeTransaction {
                    it.where(Gist::class.java).findAll().deleteAllFromRealm()
                }
            }
        }
    }

    private fun getGistRealm() = Realm.getDefaultInstance().where(Gist::class.java)

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
            Realm.getDefaultInstance().use {
                it.executeTransaction {
                    if (model.id == null) {
                        var maxId = it.where(Gist::class.java).max(GistFields.ID)?.toLong()
                        if (maxId == null) maxId = 0
                        model.id = ++maxId
                    }
                    val gist = model.toRealmModel()
                    val owner = it.where(Owner::class.java).equalTo(OwnerFields.ID, model.owner?.id).findFirstAsync()
                    owner.gists?.add(gist)
                    Timber.d("owner: $owner")
                    Timber.d("gist: $gist")
                    it.copyToRealmOrUpdate(owner)
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
            Realm.getDefaultInstance().use {
                it.copyToRealmOrUpdate(model.toRealmModel())
            }
            model
        }
    }
}