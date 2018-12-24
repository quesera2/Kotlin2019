package sera.sera.que.kotlin201x.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WikipediaPageResponse(
    val batchcomplete: String,
    @Json(name = "continue")
    val continueData: Continue,
    val query: Query
)

@JsonClass(generateAdapter = true)
data class Query(
    val searchinfo: Searchinfo,
    val search: List<WikipediaPage>
)

@JsonClass(generateAdapter = true)
data class WikipediaPage(
    val ns: Long,
    val title: String,
    val pageid: Long,
    val size: Long,
    val wordcount: Long,
    val snippet: String,
    val timestamp: String
)

@JsonClass(generateAdapter = true)
data class Searchinfo(
    val totalhits: Long
)

@JsonClass(generateAdapter = true)
data class Continue(
    val sroffset: Long,
    @Json(name = "continue")
    val continueData: String
)
