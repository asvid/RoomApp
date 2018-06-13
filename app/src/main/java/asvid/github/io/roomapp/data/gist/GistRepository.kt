package asvid.github.io.roomapp.data.gist

import asvid.github.io.roomapp.data.mappers.toModel
import asvid.github.io.roomapp.data.mappers.toRealmModel
import asvid.github.io.roomapp.data.owner.Owner
import asvid.github.io.roomapp.data.owner.OwnerFields
import asvid.github.io.roomapp.data.repository.RxCrudRepository
import asvid.github.io.roomapp.model.GistModel
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmQuery
import javax.inject.Inject

class GistRepository @Inject constructor(private val realmConfiguration: RealmConfiguration) : RxCrudRepository<GistModel, Long> {

    override fun delete(model: GistModel): Completable {
        return Completable.fromAction {
            // TODO(6) standardowa operacja na bazie bez zwracania wyniku
            Realm.getInstance(realmConfiguration).use {
                it.where(Gist::class.java)
                        .equalTo(GistFields.ID, model.id)
                        .findFirstAsync()
                        .deleteFromRealm()
            }
        }
    }

    override fun deleteAll(models: Collection<GistModel>): Completable {
        return Completable.fromAction {
            Realm.getInstance(realmConfiguration).use {
                it.executeTransaction {
                    it.where(Gist::class.java)
                            .findAllAsync()
                            .deleteAllFromRealm()
                }
            }
        }
    }

    // TODO(7) akcja wykonująca blok na RealmQuery dla klasy Gist, a następnie zamykająca instancję bazy
    private fun <T> realmAction(block: RealmQuery<Gist>.() -> T): T {
        return Realm.getInstance(realmConfiguration).use {
            return@use it.where(Gist::class.java)
                    .block()
        }
    }

    // TODO(8) zwrócenie strumienia obiektów z przemapowaniem ich na typ domenowy
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

    // TODO(9) zwrócenie konkretnego obiektu na podstaie jego ID z manualnym zamknięciem instancji bazy
    override fun fetchById(id: Long): Single<GistModel> {
        return Single.create<GistModel> {
            val realm = Realm.getInstance(realmConfiguration)
            it.onSuccess(realm.where(Gist::class.java)
                    .equalTo(GistFields.ID, id)
                    .findFirst()!!.toModel())
            realm.close()
        }
    }

    // TODO(10) dodanie nowego Gista do bazy -> wyszukanie Ownera i dodanie mu do listy obiektu Gist a następnie zapis Ownera (a nie Gista :) )
    override fun save(model: GistModel): Single<GistModel> {
        return Single.create<GistModel> {
            val realm = Realm.getInstance(realmConfiguration)
            realm.executeTransaction {
                val gist = model.toRealmModel(it)
                val owner = it.where(Owner::class.java)
                        .equalTo(OwnerFields.ID, model.owner?.id)
                        .findFirst()
                owner?.gists?.add(gist)
                it.copyToRealmOrUpdate(owner)
                model.id = gist.id
            }
            it.onSuccess(model)
            realm.close()
        }
    }

    override fun saveAll(models: Collection<GistModel>): Single<Collection<GistModel>> {
        return Single.create { }
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