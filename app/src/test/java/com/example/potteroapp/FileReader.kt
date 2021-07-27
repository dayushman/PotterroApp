package com.example.potteroapp

import java.io.InputStreamReader

object FileReader {

    val content: String

    init {
        val reader =  InputStreamReader(this.javaClass.classLoader.getResourceAsStream("Response.json"));
        content = reader.readText()
        reader.close()
    }

}