package com.example.retrofitpractice.`interface`

import com.example.retrofitpractice.model.Post
import retrofit2.Call
import retrofit2.http.GET

interface JsonPlaceHolderApi {
    @GET("posts")
    fun getPost(): Call<List<Post>>
}