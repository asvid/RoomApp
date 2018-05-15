package asvid.github.io.roomapp.model

data class OwnerModel(
        var login: String,
        var id: Long? = null,
        var gists: List<GistModel>
)