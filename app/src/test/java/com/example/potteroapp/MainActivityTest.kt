package com.example.potteroapp

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.google.gson.Gson
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivityTest {

    private val mockWebServer = MockWebServer()
    private val api: PotterApi = Retrofit.Builder()
        .baseUrl("http://127.0.0.1:8080")
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpProvider.getOkHttpClient())
        .build()
        .create(PotterApi::class.java)

    @Before
    fun setUp() {
        mockWebServer.start(8080)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
    @Test
    fun `file is read`(){
        val fileContent = FileReader("response.json").content
        Truth.assertThat(fileContent).isNotNull()
    }

    @Test
    fun `demo test case`(){
        val message = 200
        Truth.assertThat(message).isEqualTo(200)
    }
    /*@Test
    fun `api call success`(){
        val fileContent = this::class.java.classLoader.getResource("response.json").readText()

        val mockResponse = MockResponse().setResponseCode(200)
        mockWebServer.enqueue(mockResponse)
        val response = api.getCharacters().execute()

        Truth.assertThat(response.isSuccessful).isTrue()
        Truth.assertThat(response.code()).isEqualTo(200)
    }*/

}