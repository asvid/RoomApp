package asvid.github.io.roomapp.data.ownerwithgists

import io.reactivex.Flowable
import io.reactivex.Maybe
import javax.inject.Inject

class OwnerWithGistsRepository @Inject constructor(
    private var ownerWithGistsDao: OwnerWithGistsDao) {

  public fun getAllOwnersWithGists(): Flowable<List<OwnerWithGists>> {
    return ownerWithGistsDao.getOwnersWithGists()
  }

  public fun getOwnerByIdWithGists(ownerId: Long): Maybe<OwnerWithGists> {
    return ownerWithGistsDao.getOwnerByIdWithGists(ownerId)
  }

}