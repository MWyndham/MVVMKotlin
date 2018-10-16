package com.example.muhwyndham.mvvmkotlin.ui.post

import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.example.muhwyndham.mvvmkotlin.R
import com.example.muhwyndham.mvvmkotlin.base.BaseViewModel
import com.example.muhwyndham.mvvmkotlin.model.Post
import com.example.muhwyndham.mvvmkotlin.model.PostDao
import com.example.muhwyndham.mvvmkotlin.network.PostApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostListViewModel(private val postDao: PostDao):BaseViewModel(){
    @Inject
    lateinit var postApi: PostApi

    private lateinit var subscription: Disposable

    val postListAdapter: PostListAdapter = PostListAdapter()

    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadPost() }

    init {
        loadPost()
    }

    private fun loadPost() {
        subscription = Observable.fromCallable { postDao.all }
                .concatMap {
                    dbPostList ->
                    if (dbPostList.isEmpty())
                        postApi.getPost().concatMap {
                            apiPostList -> postDao.insertAll(*apiPostList.toTypedArray())
                            Observable.just(apiPostList)
                }
                    else
                        Observable.just(dbPostList)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrievePostListStart() }
                .doOnTerminate { onRetrievePostListFinish() }
                .subscribe(
                        {result -> onRetrievePostListSuccess(result)},
                        {onRetrievePostListError()}
                )
    }

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    private fun onRetrievePostListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrievePostListFinish() {
       loadingVisibility.value = View.GONE
    }

    private fun onRetrievePostListSuccess(postList: List<Post>) {
        postListAdapter.updatePostList(postList)
    }

    private fun onRetrievePostListError() {
        errorMessage.value = R.string.post_error
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}
