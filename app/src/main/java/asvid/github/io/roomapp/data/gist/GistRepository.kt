package asvid.github.io.roomapp.data.gist

import asvid.github.io.roomapp.data.mappers.toModel
import asvid.github.io.roomapp.data.mappers.toRealmModel
import asvid.github.io.roomapp.data.repository.RxCrudRepository
import asvid.github.io.roomapp.model.GistModel
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import io.realm.Realm
import io.realm.RealmQuery
import javax.inject.Inject


class GistRepository @Inject constructor(val realm: Realm) :
        RxCrudRepository<GistModel, Gist, Long> {

    override val repositoryDb: RealmQuery<Gist> = realm.where(Gist::class.java)

    override fun delete(model: GistModel): Completable {
        return Completable.fromAction {
            Realm.getDefaultInstance().executeTransaction {
                it.where(Gist::class.java).equalTo("id", model.id).findFirstAsync().deleteFromRealm()
            }
        }
    }

    override fun deleteAll(models: Collection<GistModel>): Completable {
        return Completable.fromAction {
            Realm.getDefaultInstance().executeTransaction {
                it.where(Gist::class.java).findAll().deleteAllFromRealm()
            }
        }
    }

    override fun fetchAll(): Flowable<Collection<GistModel>> {
        return repositoryDb.findAll().let { it.asFlowable().map { it.map { it.toModel() } } }

    }

    override fun fetchById(id: Long): Maybe<GistModel> {
        return Maybe.fromAction { repositoryDb.equalTo("id", id).findFirstAsync() }
    }

    override fun save(model: GistModel): Single<GistModel> {
        return Single.fromCallable {
            Realm.getDefaultInstance().executeTransactionAsync {
                if (model.id == null) {
                    var maxId = it.where(Gist::class.java).max("id")?.toLong()
                    if (maxId == null) maxId = 0
                    model.id = ++maxId
                }
                val gist = it.copyToRealmOrUpdate(model.toRealmModel())
                model.owner.toRealmModel().gists.add(gist)
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
            realm.copyToRealmOrUpdate(model.toRealmModel()).toModel()
        }
    }
}