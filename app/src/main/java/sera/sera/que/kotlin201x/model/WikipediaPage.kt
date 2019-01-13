package sera.sera.que.kotlin201x.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WikipediaPageResponse(
    val batchcomplete: String,
    @SerialName("continue")
    val continueData: Continue,
    val query: Query
)

@Serializable
data class Query(
    val searchinfo: Searchinfo,
    val search: List<WikipediaPage>
)

@Serializable
data class WikipediaPage(
    val ns: Long,
    val title: String,
    val pageid: Long,
    val size: Long,
    val wordcount: Long,
    val snippet: String,
    val timestamp: String
)

@Serializable
data class Searchinfo(
    val totalhits: Long
)

@Serializable
data class Continue(
    val sroffset: Long,
    @SerialName("continue")
    val continueData: String
)
