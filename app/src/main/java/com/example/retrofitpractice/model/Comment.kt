package com.example.retrofitpractice.model

import com.google.gson.annotations.SerializedName

class Comment {
    var postId = 0
    var id = 0
    var name = ""
    var email = ""
    @SerializedName("body")
    var text = ""

}