package asvid.github.io.roomapp.model

data class OwnerModel(
        var login: String? = null,
        var id: Long? = null
)

data class OwnerWithGistsModel(
        var login: String? = null,
        var id: Long? = null,
        var gists: List<GistModel>
)