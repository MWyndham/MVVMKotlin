package com.example.muhwyndham.mvvmkotlin.ui.post

import android.arch.lifecycle.MutableLiveData
import com.example.muhwyndham.mvvmkotlin.base.BaseViewModel
import com.example.muhwyndham.mvvmkotlin.model.Post

class PostViewModel : BaseViewModel() {
    private val postTitle = MutableLiveData<String>()
    private val postBody = MutableLiveData<String>()

    fun bind(post: Post){
        postTitle.value = post.title
        postBody.value = post.body
    }

    fun getPostTitle(): MutableLiveData<String>{
        return postTitle
    }

    fun getPostBody(): MutableLiveData<String>{
        return postBody
    }
}