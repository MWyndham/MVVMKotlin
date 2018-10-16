package com.example.muhwyndham.mvvmkotlin.base

import android.arch.lifecycle.ViewModel
import com.example.muhwyndham.mvvmkotlin.injection.component.DaggerViewModelInjector
import com.example.muhwyndham.mvvmkotlin.injection.component.ViewModelInjector
import com.example.muhwyndham.mvvmkotlin.injection.module.NetworkModule
import com.example.muhwyndham.mvvmkotlin.ui.post.PostListViewModel

abstract class BaseViewModel : ViewModel(){

    private val injector: ViewModelInjector = DaggerViewModelInjector
            .builder()
            .networkModule(NetworkModule)
            .build()

    init{
        inject()
    }

    private fun inject(){
        when(this) {
            is PostListViewModel -> injector.inject(this)
        }
    }

}