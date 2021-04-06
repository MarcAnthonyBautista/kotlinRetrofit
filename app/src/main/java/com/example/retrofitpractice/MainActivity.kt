package com.example.retrofitpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retrofitpractice.`interface`.JsonPlaceHolderApi
import com.example.retrofitpractice.common.Constants
import com.example.retrofitpractice.model.Post
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRetrofit()

    }
    fun initRetrofit(){
        val retrofit=Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var jsonPlaceHolderApi:JsonPlaceHolderApi=retrofit.create(JsonPlaceHolderApi::class.java)
        var call = jsonPlaceHolderApi.getPost()
        call.enqueue(object : Callback<List<Post>?> {
            override fun onFailure(call: Call<List<Post>?>, t: Throwable) {
                textOutput.setText(t.message)
            }

            override fun onResponse(call: Call<List<Post>?>, response: Response<List<Post>?>) {
               if(!response.isSuccessful){
                   showText("Code: "+response.code())
                   return
               }
               var posts=response.body()
                for(post in posts!!){
                    var content=""
                    content +=""+post.userId+"\n"
                    content +=""+post.id+"\n"
                    content +=""+post.title+"\n"
                    content +=""+post.text+"\n\n"
                    showText(content)
                }
            }
        })
    }

    fun showText(value:String){
        textOutput.append(value)
    }


}