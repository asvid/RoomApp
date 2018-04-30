package asvid.github.io.roomapp.data.ownerwithgists

import asvid.github.io.roomapp.model.OwnerWithGistsModel
import asvid.github.io.roomapp.model.model
import asvid.github.io.roomapp.model.toModel
import io.reactivex.Flowable
import io.reactivex.Maybe
import javax.inject.Inject

class OwnerWithGistsRepository @Inject constructor(
        private var ownerWithGistsDao: OwnerWithGistsDao) {

    public fun getAllOwnersWithGists(): Flowable<List<OwnerWithGistsModel>> {
        return ownerWithGistsDao.getOwnersWithGists().map { it.model() }
    }

    public fun getOwnerByIdWithGists(ownerId: Long): Maybe<OwnerWithGistsModel> {
        return ownerWithGistsDao.getOwnerByIdWithGists(ownerId).map { it.toModel() }
    }

}