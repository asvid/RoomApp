package asvid.github.io.roomapp.api.pojo

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.Date

data class Gist(
    val id: String,
    val description: String,
    val comments: String,
    val url: String,
    @SerializedName("forks_url") val forksUrl: String,
    @SerializedName("commits_url") val commitsUrl: String,
    @SerializedName("git_pull_url") val gitPullUrl: String,
    @SerializedName("git_push_url") val gitPushUrl: String,
    @SerializedName("html_url") val htmlUrl: String,
    @SerializedName("comments_url") val commentsUrl: String,
    @SerializedName("created_at") val createdAt: Date,
    @SerializedName("updated_at") val updatedAt: Date,
    val public: Boolean,
    val truncated: Boolean,
    val owner: Owner
) : Serializable