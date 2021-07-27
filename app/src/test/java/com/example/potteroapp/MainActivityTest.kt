package com.example.potteroapp

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.google.gson.Gson
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
    val api: PotterApi = Retrofit.Builder()
        .baseUrl("http://127.0.0.1:8080")
        .addConverterFactory(GsonConverterFactory.create(Gson()))
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
        Truth.assertThat(FileReader.content).isNotNull()
    }

}