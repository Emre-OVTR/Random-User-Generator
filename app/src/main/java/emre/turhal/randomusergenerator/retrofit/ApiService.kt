package emre.turhal.randomusergenerator.retrofit

import emre.turhal.randomusergenerator.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    //Endpoint entry
    @GET("/api/")
    //query to get 20 users by request
    suspend fun getResults(@Query("results") count:Int) : Response<ApiResponse>
}