package asvid.github.io.roomapp.model

data class OwnerModel(
        var id: Long? = null,
        var login: String,
        var gists: List<GistModel>
)