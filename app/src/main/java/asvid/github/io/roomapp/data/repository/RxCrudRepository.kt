package asvid.github.io.roomapp.data.repository

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import io.realm.RealmQuery

interface RxCrudRepository<M, RM, I> : Repository<M, RM, I> {

    val repositoryDb: RealmQuery<RM>

    fun delete(model: M): Completable

    fun deleteAll(models: Collection<M>): Completable

    fun fetchAll(): Flowable<Collection<M>>

    fun fetchById(id: I): Maybe<M>

    fun save(model: M): Single<M>

    fun saveAll(models: Collection<M>): Single<Collection<M>>
}
