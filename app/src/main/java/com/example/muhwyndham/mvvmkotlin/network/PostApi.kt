package com.example.muhwyndham.mvvmkotlin.network

import com.example.muhwyndham.mvvmkotlin.model.Post
import io.reactivex.Observable
import retrofit2.http.GET

interface PostApi {


    @GET("/posts")
    fun getPost() : Observable<List<Post>>
}