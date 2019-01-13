package sera.sera.que.kotlin201x.api

import sera.sera.que.kotlin201x.model.WikipediaPageResponse

interface WikipediaSearchService {
    suspend fun search(query: String): WikipediaPageResponse
}

