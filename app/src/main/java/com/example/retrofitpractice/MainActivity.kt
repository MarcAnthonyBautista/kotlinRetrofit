package com.example.retrofitpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retrofitpractice.`interface`.JsonPlaceHolderApi
import com.example.retrofitpractice.common.Constants
import com.example.retrofitpractice.model.Comment
import com.example.retrofitpractice.model.Post
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var jsonPlaceHolderApi:JsonPlaceHolderApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()

    }
    fun init(){
        //Retrofit
        val retrofit=Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        //JsonPlaceHolderAPi
        jsonPlaceHolderApi=retrofit.create(JsonPlaceHolderApi::class.java)
       // getPost()
        getComments()
    }

     fun getPost(){
         var parameter : HashMap<String,String> = HashMap()
         parameter.put("userId","1")
         parameter.put("_sort","id")
         parameter.put("_order","desc")
         var call = jsonPlaceHolderApi.getPost(parameter)
         call.enqueue(object : Callback<List<Post>?> {
             override fun onFailure(call: Call<List<Post>?>, t: Throwable) {

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
    fun getComments(){
        var call=jsonPlaceHolderApi.getComments(3)
        call.enqueue(object : Callback<List<Comment>?> {

            override fun onFailure(call: Call<List<Comment>?>, t: Throwable) {
                textOutput.setText(t.message)
            }

            override fun onResponse(
                call: Call<List<Comment>?>,
                response: Response<List<Comment>?>
            ) {
                if(!response.isSuccessful){
                    showText("Code: "+response.code())
                    return
                }
                var comments=response.body()
                for(comment in comments!!){
                    var content=""
                    content +=""+comment.postId+"\n"
                    content +=""+comment.id+"\n"
                    content +=""+comment.name+"\n"
                    content +=""+comment.email+"\n"
                    content +=""+comment.text+"\n\n"
                    showText(content)
                }
            }
        })
    }
    fun showText(value:String){
        textOutput.append(value)
    }


}