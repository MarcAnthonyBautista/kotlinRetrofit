package com.example.retrofitpractice.`interface`

import com.example.retrofitpractice.model.Comment
import com.example.retrofitpractice.model.Post
import retrofit2.Call
import retrofit2.http.*

interface JsonPlaceHolderApi {
    @GET("posts")
    fun getPost(
        @Query("userId") userId: IntArray,
        @Query("_sort") sort: String?,
        @Query("_order") order: String?
    ): Call<List<Post>>

    @GET("posts")
    fun getPost(@QueryMap parameters:Map<String, String>): Call<List<Post>>

    @GET("posts/{id}/comments")
    fun getComments(@Path("id") postId: Int):Call<List<Comment>>

    @GET
    fun getComments(@Url url:String) : Call<List<Comment>>

    @POST("posts")
    fun createPost(@Body post:Post) : Call<Post>

    @FormUrlEncoded
    @POST("posts")
    fun createPost(
            @Field("userid") userId:Int,
            @Field("title") title:String,
            @Field("body")  text:String
    ):Call<Post>

    @FormUrlEncoded
    @POST("posts")
    fun createPost(@FieldMap field: Map<String,String>):Call<Post>

    @PUT("posts/{id}")
    fun putPost(@Path("id")id:Int, @Body post:Post):Call<Post>

    @PATCH("posts/{id}")
    fun patchPost(@Path("id")id:Int, @Body post:Post):Call<Post>

    @DELETE("posts/{id}")
    fun deletePost(@Path("id")id:Int):Call<Void>
}