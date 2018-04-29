package asvid.github.io.roomapp.model

import java.io.Serializable
import java.util.Date

data class GistModel(
    var id: Long?,
    var description: String,
    var ownerId: Long,
    var starred: Boolean,
    var date: Date
) : Serializable

data class GistWithOwnerModel(
    var id: Long?,
    var description: String,
    var owner: OwnerModel,
    var starred: Boolean,
    var date: Date
) : Serializable