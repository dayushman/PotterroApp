package com.example.potteroapp

class PotterTestApp : PotterApp() {

    var url = "http://127.0.0.1:8080"

    override fun getBaseUrl(): String {
        return url
    }
}