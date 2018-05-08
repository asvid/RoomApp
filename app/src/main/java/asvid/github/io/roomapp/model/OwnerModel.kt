package asvid.github.io.roomapp.model

data class OwnerModel(
        val login: String,
        var id: Long? = null,
        var avatarUrl: String? = null
)

data class OwnerWithGistsModel(
        var login: String,
        var id: Long? = null,
        var avatarUrl: String? = null,
        var gists: List<GistModel>
)