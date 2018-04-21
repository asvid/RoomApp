package asvid.github.io.roomapp.model

data class OwnerModel(
    val login: String? = null,
    val url: String? = null,
    val avatarUrl: String? = null,
    val ownerId: Long = -1,
    var id: Long? = null
)