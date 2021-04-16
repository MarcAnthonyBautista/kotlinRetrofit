package com.example.retrofitpractice.model

import com.google.gson.annotations.SerializedName

class Post {
    var userId = 0
    var id = 0
    var title = "";
    @SerializedName("body")
    var text=""

    constructor(userId: Int, title: String, text: String) {
        this.userId = userId
        this.title = title
        this.text = text
    }
}