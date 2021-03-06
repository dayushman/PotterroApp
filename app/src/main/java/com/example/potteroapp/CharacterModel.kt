
package com.example.potteroapp

import com.google.gson.annotations.SerializedName

data class CharacterModel(
    @SerializedName("name")
    val name: String,
    @SerializedName("house")
    val house: String? = "Unknown",
    @SerializedName("ancestry")
    val ancestry: String? = "Unknown"
)