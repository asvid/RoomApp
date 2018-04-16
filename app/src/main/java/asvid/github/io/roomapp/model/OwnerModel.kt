package asvid.github.io.roomapp.model

data class OwnerModel(
        val login: String? = null,
        val url: String? = null,
        val avatarUrl: String? = null,
        val id: Long = -1,
        var dbId: Long? = null
)