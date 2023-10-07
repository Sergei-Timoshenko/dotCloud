package com.origamitown.dotcloud.userinterface.main.fragments.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.origamitown.dotcloud.models.post.Post

class FeedViewModel : ViewModel() {

    private val _friendPosts = MutableLiveData<List<Post>>()
    var friendPosts: LiveData<List<Post>> = _friendPosts

    private val _globalPosts = MutableLiveData<List<Post>>()
    var globalPosts: LiveData<List<Post>> = _globalPosts

    fun setFriendPosts(posts: List<Post>) {
        _friendPosts.value = posts
    }

    fun setGlobalPosts(posts: List<Post>) {
        _globalPosts.value = posts
    }
}