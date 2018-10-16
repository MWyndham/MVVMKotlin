package com.example.muhwyndham.mvvmkotlin.injection.component

import com.example.muhwyndham.mvvmkotlin.injection.module.NetworkModule
import com.example.muhwyndham.mvvmkotlin.ui.post.PostListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    /**
     * Inject required dependencies into the specified PostListViewModel
     * @param postListViewModel PostListViewModel in which to inject the dependencies
     */
    fun inject(postListViewModel: PostListViewModel)

    @Component.Builder
    interface Builder{
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}