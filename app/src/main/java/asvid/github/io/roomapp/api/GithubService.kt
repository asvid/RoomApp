package asvid.github.io.roomapp.api

import asvid.github.io.roomapp.api.pojo.Gist
import retrofit2.Call
import retrofit2.http.GET

interface GitHubService {

  @GET("/gists/public")
  fun listRepos(): Call<List<Gist>>
}