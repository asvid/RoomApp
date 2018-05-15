package asvid.github.io.roomapp.data.gist

import android.util.Log
import asvid.github.io.roomapp.data.repository.RxCrudRepository
import asvid.github.io.roomapp.model.GistModel
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import io.realm.RealmQuery
import io.realm.RealmResults
import javax.inject.Inject


class GistRepository @Inject constructor(override val gistDatabase: RealmQuery<Gist>) :
        RxCrudRepository<GistModel, Gist, Long> {

    override fun delete(model: GistModel): Completable {
        return Completable.fromAction {
            gistDatabase.equalTo("id", model.id).findFirst()?.deleteFromRealm()
        }
    }

    override fun deleteAll(models: Collection<GistModel>): Completable {
        return Completable.fromAction {
            gistDatabase.findAll().deleteAllFromRealm()
        }
    }

    override fun fetchAll(): Flowable<Collection<GistModel>> {
        val flowable: Flowable<RealmResults<GistModel>>
        flowable = gistDatabase.findAll()
                .asFlowable()

        return flowable
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

    fun update(model: GistModel): Single<GistModel> {
        return Single.fromCallable {
            gistDao.update(model.toEntity())
            model
        }
    }
}