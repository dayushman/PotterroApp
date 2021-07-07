package com.example.potteroapp

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import androidx.test.rule.ActivityTestRule
import com.jakewharton.espresso.OkHttp3IdlingResource
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@MediumTest
class MainActivityTest{

/*    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java, true, false)*/

    private val mockServer = MockWebServer()
    private val idlingResource = OkHttp3IdlingResource.create("okhttp", OkHttpProvider.getOkHttpClient())
    @Before
    fun setup() {
        mockServer.start(8080)
//        IdlingRegistry.getInstance().register(idlingResource)
    }

    /*@Test
    fun testSuccessfulResponse(){
        mockServer.dispatcher = object : Dispatcher(){
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse().setBody(FileReader.readStringFromFile("response.json"))
                    .setResponseCode(200)
            }
        }
        activityRule.launchActivity(null)
        onView(withId(R.id.character_recyclerview))
            .check(matches(isDisplayed()))
    }*/

    @Test
    fun testingSuccessApi(){
        mockServer.dispatcher = object : Dispatcher(){
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse().setBody(FileReader.readStringFromFile("response.json"))
                    .setResponseCode(200)
            }
        }

        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.character_recyclerview)).check(matches(isDisplayed()))
    }

    @Test
    fun testFailureApi(){
        mockServer.dispatcher = object : Dispatcher(){
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse().setResponseCode(500)
            }
        }

        ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.textview)).check(matches(isDisplayed()))
    }

    @After
    fun teardown(){
        mockServer.shutdown()
    }
}