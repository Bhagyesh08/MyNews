package com.example.mynews

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//https://newsapi.org/v2/top-headlines?country=in&apiKey=87575d9e255d429185cf24b5454cf0df
const val BASE_URL="https://newsapi.org/"
const val API_KEY="87575d9e255d429185cf24b5454cf0df"
interface NewsInterface {
    @GET("/v2/top-headlines?apiKey=$API_KEY")
    fun getHeadlines(@Query("country")country : String,@Query("page")page:Int) :  Call<News>

}
object Service{
    val newsInstance:NewsInterface
    init {
       val retrofit=Retrofit.Builder()
           .baseUrl(BASE_URL)
           .addConverterFactory(GsonConverterFactory.create())
           .build()
        newsInstance=retrofit.create(NewsInterface::class.java)
    }
}