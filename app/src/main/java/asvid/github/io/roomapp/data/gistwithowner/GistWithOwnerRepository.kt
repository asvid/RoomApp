package asvid.github.io.roomapp.data.gistwithowner

import asvid.github.io.roomapp.model.GistWithOwnerModel
import asvid.github.io.roomapp.model.toModel
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class GistWithOwnerRepository @Inject constructor(var gistWithOwnerDao: GistWithOwnerDao)
{
  fun fetchAll(): Flowable<Collection<GistWithOwnerModel>> {
    return gistWithOwnerDao.getGistsWithOwners().map { it.toModel() }
  }

  fun save(model: GistWithOwnerModel): Single<GistWithOwnerModel> {
    return gistWithOwnerDao.saveOwnerAndGist(model)
  }
}