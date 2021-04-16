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
        //getPost()
        //getPostArrayId()
        //getComments()
        //createPost()
        //createPost2()
        //createPost3()
        updatePost()
        //deletePost()

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
    fun getPostArrayId(){
        var call = jsonPlaceHolderApi.getPost(intArrayOf(2,3,6),"id","desc")
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
        var call = jsonPlaceHolderApi.getComments("comments")
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

    fun createPost(){
        var post = Post(23,"New Title", "new Text")
        var call: Call<Post> =jsonPlaceHolderApi.createPost(post)
        call.enqueue(object : Callback<Post?> {
            override fun onResponse(call: Call<Post?>, response: Response<Post?>) {
                var postResponse=response.body()
                var content=""
                content +=""+"Code: "+response.code()+"\n"
                content +=""+postResponse?.userId+"\n"
                content +=""+postResponse?.id+"\n"
                content +=""+postResponse?.title+"\n"
                content +=""+postResponse?.text+"\n\n"
                showText(content)
            }

            override fun onFailure(call: Call<Post?>, t: Throwable) {
                textOutput.setText(t.message)
            }
        })
    }

    fun createPost2(){
        var call:Call<Post> = jsonPlaceHolderApi.createPost(23,"title2","body2")
        call.enqueue(object : Callback<Post?> {
            override fun onResponse(call: Call<Post?>, response: Response<Post?>) {
                var postResponse=response.body()
                var content=""
                content +=""+"Code: "+response.code()+"\n"
                content +=""+postResponse?.userId+"\n"
                content +=""+postResponse?.id+"\n"
                content +=""+postResponse?.title+"\n"
                content +=""+postResponse?.text+"\n\n"
                showText(content)
            }

            override fun onFailure(call: Call<Post?>, t: Throwable) {
                textOutput.setText(t.message)
            }
        })

    }

    fun createPost3(){
        var parameter:HashMap<String,String> = HashMap()
        parameter.put("userid","5")
        parameter.put("title","Title")
        var call=jsonPlaceHolderApi.createPost(parameter)
        call.enqueue(object : Callback<Post?> {
            override fun onResponse(call: Call<Post?>, response: Response<Post?>) {
                var postResponse=response.body()
                var content=""
                content +=""+"Code: "+response.code()+"\n"
                content +=""+postResponse?.userId+"\n"
                content +=""+postResponse?.id+"\n"
                content +=""+postResponse?.title+"\n"
                content +=""+postResponse?.text+"\n\n"
                showText(content)
            }

            override fun onFailure(call: Call<Post?>, t: Throwable) {
                textOutput.setText(t.message)
            }
        })
    }

    fun updatePost(){
        var post=Post(202,"new title","newText")
        var call= jsonPlaceHolderApi.putPost(2300,post)
        call.enqueue(object : Callback<Post?> {
            override fun onResponse(call: Call<Post?>, response: Response<Post?>) {
                if(!response.isSuccessful){
                    showText("Code: "+response.code())
                    return
                }
                var postResponse=response.body()
                var content=""
                content +=""+"Code: "+response.code()+"\n"
                content +="id: " +postResponse?.id+"\n"
                content +="userId: "+postResponse?.userId+"\n"
                content +="title: "+postResponse?.title+"\n"
                content +="text: "+postResponse?.text+"\n\n"
                showText(content)
            }

            override fun onFailure(call: Call<Post?>, t: Throwable) {
                textOutput.setText(t.message)
            }
        })
    }
    fun deletePost(){
        var call=jsonPlaceHolderApi.deletePost(5)
        call.enqueue(object : Callback<Void?> {
            override fun onResponse(call: Call<Void?>, response: Response<Void?>) {
               textOutput.setText("Code: " +response.code())
            }

            override fun onFailure(call: Call<Void?>, t: Throwable) {
                textOutput.setText(t.message)
            }
        })
    }
}