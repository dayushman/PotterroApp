

package com.example.potteroapp

import android.app.Application

open class PotterApp : Application() {
  open fun getBaseUrl() = "http://hp-api.herokuapp.com"
}