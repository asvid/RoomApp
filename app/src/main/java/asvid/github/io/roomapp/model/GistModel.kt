package asvid.github.io.roomapp.model

import java.util.*

data class GistModel(
        var id: Long?,
        var description: String,
        var owner: OwnerModel? = null,
        var starred: Boolean,
        var date: Date
)