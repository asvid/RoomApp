package asvid.github.io.roomapp.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object Api {

  private
  val gson = GsonBuilder()
      .serializeNulls()
      .setLenient()
      .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
      .create()

  private val retrofit = Retrofit.Builder()
      .baseUrl("https://api.github.com")
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .addConverterFactory(GsonConverterFactory.create(gson))
      .build()

  val gitHub = retrofit.create(GitHub::class.java)

}