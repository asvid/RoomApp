package asvid.github.io.roomapp.model

import java.io.Serializable

data class GistModel(
    var id: Long?,
    val gistId: String,
    val description: String,
    val comments: String,
    val url: String,
    val owner: OwnerModel,
    var starred: Boolean
) : Serializable