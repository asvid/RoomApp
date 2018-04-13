package asvid.github.io.roomapp.model

import java.io.Serializable

data class GistModel(
    val id: String,
    val description: String,
    val comments: String,
    val user: String?,
    val url: String
) : Serializable