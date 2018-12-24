package sera.sera.que.kotlin201x.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import sera.sera.que.kotlin201x.model.WikipediaPageResponse

interface WikipediaSearchService {

    @GET("/w/api.php?format=json&action=query&list=search")
    fun search(@Query("srsearch") query: String): Call<WikipediaPageResponse>
}