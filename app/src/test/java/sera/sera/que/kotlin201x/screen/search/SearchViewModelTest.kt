package sera.sera.que.kotlin201x.screen.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import kotlinx.serialization.json.JSON
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import sera.sera.que.kotlin201x.api.WikipediaSearchService
import sera.sera.que.kotlin201x.model.WikipediaPage
import sera.sera.que.kotlin201x.model.WikipediaPageResponse

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class SearchViewModelTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    private lateinit var target: SearchViewModel
    private lateinit var searchService: WikipediaSearchService

    private lateinit var searchResultCapture: CapturingSlot<List<WikipediaPage>>
    private lateinit var searchQuery: Observer<String>

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)

        val dummyResponse = JSON.parse(WikipediaPageResponse.serializer(), dummyJson)
        searchService = mockk {
            coEvery { search(any()) } returns dummyResponse
        }
        searchQuery = spyk()

        target = SearchViewModel(searchService)
        searchResultCapture = slot()
        val searchResultObserver = mockk<Observer<List<WikipediaPage>>> {
            every { onChanged(capture(searchResultCapture)) } just Runs
        }
        target.searchResult.observeForever(searchResultObserver)
        target.searchQuery.observeForever(searchQuery)
    }

    @Test
    fun testDummy() {
        target.searchQuery.postValue("search")

        runBlocking {
            target.onSearchClick()
        }

        coVerify {
            searchService.search("search")
        }

        assertThat(searchResultCapture.captured).hasSize(2)
        val firstItem = searchResultCapture.captured.first()
        assertThat(firstItem.pageid).isEqualTo(1652941)
        assertThat(firstItem.title).isEqualTo("深愛")
    }

    private val dummyJson = """
{
    "batchcomplete": "",
    "continue": {
        "sroffset": 10,
        "continue": "-||"
    },
    "query": {
        "searchinfo": {
            "totalhits": 256
        },
        "search": [
            {
                "ns": 0,
                "title": "深愛",
                "pageid": 1652941,
                "size": 13587,
                "wordcount": 1154,
                "snippet": "ダミー",
                "timestamp": "2018-10-29T07:53:57Z"
            },
            {
                "ns": 0,
                "title": "深愛〜only one〜",
                "pageid": 1314482,
                "size": 4190,
                "wordcount": 322,
                "snippet": "ダミー",
                "timestamp": "2017-04-19T20:45:05Z"
            }
        ]
    }
}
"""

}

