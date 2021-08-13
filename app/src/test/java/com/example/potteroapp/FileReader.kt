package com.example.potteroapp

import java.io.InputStreamReader

class FileReader(path:String) {
    val content: String = this::class.java.classLoader.getResource(path).readText()


}
