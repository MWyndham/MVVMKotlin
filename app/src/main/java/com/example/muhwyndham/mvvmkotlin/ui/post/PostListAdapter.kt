package com.example.muhwyndham.mvvmkotlin.ui.post

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.muhwyndham.mvvmkotlin.R
import com.example.muhwyndham.mvvmkotlin.databinding.ItemPostBinding
import com.example.muhwyndham.mvvmkotlin.model.Post

class PostListAdapter: RecyclerView.Adapter<PostListAdapter.ViewHolder>(){


    private lateinit var postList: List<Post>


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListAdapter.ViewHolder {
        val binding: ItemPostBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_post, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (::postList.isInitialized) postList.size else 0
    }

    override fun onBindViewHolder(holder: PostListAdapter.ViewHolder, position: Int) {
        holder.bind(postList[position])
    }

    fun updatePostList(postList: List<Post>){
        this.postList = postList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemPostBinding):RecyclerView.ViewHolder(binding.root){
        private val viewModel = PostViewModel()
        fun bind(post: Post){
            viewModel.bind(post)
            binding.viewModel = viewModel
        }
    }

}