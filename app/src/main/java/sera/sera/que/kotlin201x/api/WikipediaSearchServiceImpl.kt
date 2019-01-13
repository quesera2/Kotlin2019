package sera.sera.que.kotlin201x.api

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import okhttp3.logging.HttpLoggingInterceptor
import sera.sera.que.kotlin201x.model.WikipediaPageResponse

object WikipediaSearchServiceImpl : WikipediaSearchService {

    private val client = HttpClient(OkHttp) {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
        engine {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            addInterceptor(loggingInterceptor)
        }
    }

    override suspend fun search(query: String): WikipediaPageResponse {
        return client.get {
            accept(ContentType.Application.Json)
            url {
                protocol = URLProtocol.HTTPS
                host = "ja.wikipedia.org"
                path("w", "api.php")
                parameters.apply {
                    append("format", "json")
                    append("action", "query")
                    append("list", "search")
                    append("srsearch", query)
                }
            }
        }
    }
}