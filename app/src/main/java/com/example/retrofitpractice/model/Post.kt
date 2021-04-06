package com.example.retrofitpractice.model

import com.google.gson.annotations.SerializedName

class Post {
    var userId = 0
    var id = 0
    var title = "";
    @SerializedName("body")
    var text=""
}